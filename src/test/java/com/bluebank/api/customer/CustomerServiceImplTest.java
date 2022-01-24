package com.bluebank.api.customer;

import com.bluebank.api.account.Account;
import com.bluebank.api.account.AccountMapper;
import com.bluebank.api.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @Spy
    private final AccountMapper accountMapper = Mockito.mock(AccountMapper.class, Mockito.RETURNS_DEEP_STUBS);
    @Mock
    private Clock clock;

    @Test
    void save() {
        // given
        Long id = 1L;
        Customer entity = Customer.builder().id(id).surname("test").build();
        when(customerRepository.save(entity)).thenReturn(entity);

        // when
        CustomerDTO newDTO = customerService.save(customerMapper.toDTO(entity));

        // then
        assertThat(newDTO.getId()).isEqualTo(id);
    }

    @Test
    void getById() {
        // given
        Long id = 1L;
        BigDecimal initalCreditSum = BigDecimal.valueOf(10);


        Transaction transaction = Transaction.builder().credit(BigDecimal.ONE).transactionDate(Date.from(Instant.now())).build();

        List<Account> accounts = List.of(
                Account.builder().initialCredit(BigDecimal.valueOf(5)).transactions(List.of(transaction)).build(),
                Account.builder().initialCredit(BigDecimal.valueOf(5)).build()
        );

        Customer entity = Customer.builder().id(id).surname("test").accounts(accounts).build();
        when(customerRepository.getById(id)).thenReturn(entity);

        // when
        CustomerDTO newDTO = customerService.getById(id);

        // then
        BalanceDTO balanceDTO = BalanceDTO.builder()
                .balance(BigDecimal.ZERO)
                .accounts(accountMapper.toDTOs(accounts))
                .balance(initalCreditSum)
                .build();
        assertThat(newDTO.getBalance()).isEqualTo(balanceDTO);
    }
}