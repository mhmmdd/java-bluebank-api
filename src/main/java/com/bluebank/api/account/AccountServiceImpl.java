package com.bluebank.api.account;

import com.bluebank.api.transaction.Transaction;
import com.bluebank.api.transaction.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final Clock clock;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, AccountMapper accountMapper, Clock clock) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
        this.clock = clock;
    }

    @Override
    @Transactional
    public AccountDTO createAccount(AccountDTO dto) {
        Account account = accountMapper.toEntity(dto);
        account.setAccountType(Account.AccountTypeEnum.DEBIT);

        Account newAccount = accountRepository.save(account);

        if (newAccount.getInitialCredit() != null && newAccount.getInitialCredit().intValue() > 0) {
            transactionRepository.save(
                    Transaction.builder()
                            .accountId(newAccount.getId())
                            .transactionDate(Date.from(Instant.now(clock)))
                            .credit(newAccount.getInitialCredit())
                            .build());
        }
        return accountMapper.toDTO(newAccount);
    }
}
