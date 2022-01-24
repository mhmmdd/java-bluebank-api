package com.bluebank.api.customer;

public interface CustomerService {
    CustomerDTO save(CustomerDTO dto);
    CustomerDTO getById(Long customerId);
}