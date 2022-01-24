package com.bluebank.api.account;

import com.bluebank.api.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountDTO implements Serializable {
    private Long id;
    private Long customerId;
    private BigDecimal initialCredit;
    private List<TransactionDTO> transactions;
}
