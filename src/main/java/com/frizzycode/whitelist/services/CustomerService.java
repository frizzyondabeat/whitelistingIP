package com.frizzycode.whitelist.services;

import com.frizzycode.whitelist.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable page);
}
