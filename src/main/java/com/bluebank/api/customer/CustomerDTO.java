package com.bluebank.api.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private BalanceDTO balance;
}
