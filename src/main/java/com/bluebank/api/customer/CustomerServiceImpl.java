package com.bluebank.api.customer;

import com.bluebank.api.account.Account;
import com.bluebank.api.account.AccountMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               AccountMapper accountMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO dto) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(dto)));
    }

    @Override
    public CustomerDTO getById(Long customerId) {
        Customer customer = customerRepository.getById(customerId);
        List<Account> accounts = customer.getAccounts();

        BalanceDTO balanceDTO = BalanceDTO.builder()
                .balance(BigDecimal.ZERO)
                .build();

        for (Account account : accounts) {
            balanceDTO.setBalance(balanceDTO.getBalance().add(account.getInitialCredit()));
            account.setTransactions(account.getTransactions());
        }
        balanceDTO.setAccounts(accountMapper.toDTOs(accounts));

        CustomerDTO dto = customerMapper.toDTO(customer);
        dto.setBalance(balanceDTO);
        return dto;
    }
}
