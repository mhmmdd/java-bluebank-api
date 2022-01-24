package com.bluebank.api.account;

import com.bluebank.api.transaction.Transaction;
import com.bluebank.api.transaction.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Spy
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
    @Mock
    private Clock clock;

    @Test
    void createAccount_initialCreditIsZero() {
        // given
        Long id = 1L;
        Account entity = Account.builder().id(id).accountType(Account.AccountTypeEnum.DEBIT).build();
        when(accountRepository.save(entity)).thenReturn(entity);

        // when
        AccountDTO newDTO = accountService.createAccount(accountMapper.toDTO(entity));

        // then
        assertThat(newDTO.getId()).isEqualTo(id);
    }

    @Test
    void createAccount_initialCredit() {
        // given
        Long id = 1L;
        Account account = Account.builder().id(id).accountType(Account.AccountTypeEnum.DEBIT).initialCredit(BigDecimal.ONE).build();
        when(accountRepository.save(account)).thenReturn(account);

        Instant instant = Instant.now();
        when(clock.instant()).thenReturn(instant);

        Transaction transaction = Transaction.builder()
                .accountId(account.getId())
                .credit(account.getInitialCredit())
                .transactionDate(Date.from(instant)).build();

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // when
        AccountDTO newDTO = accountService.createAccount(accountMapper.toDTO(account));

        // then
        assertThat(newDTO.getId()).isEqualTo(id);
    }
}