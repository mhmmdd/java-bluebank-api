package com.bluebank.api.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionDTO implements Serializable {
    private Long id;
    private BigDecimal credit;
    private Long accountId;
    private Date transactionDate;
}
