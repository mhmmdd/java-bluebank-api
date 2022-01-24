package com.bluebank.api.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({
            @Mapping(source = "createdAt", target = "transactionDate")
    })
    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO dto);
}
