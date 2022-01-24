package com.bluebank.api.account;

import com.bluebank.api.transaction.TransactionMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface AccountMapper {
    AccountDTO toDTO(Account account);

    Account toEntity(AccountDTO dto);

    List<AccountDTO> toDTOs(List<Account> accounts);
}
