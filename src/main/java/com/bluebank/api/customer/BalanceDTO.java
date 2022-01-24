package com.bluebank.api.customer;

import com.bluebank.api.account.AccountDTO;
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
public class BalanceDTO implements Serializable {
    private BigDecimal balance;
    private List<AccountDTO> accounts;
}
