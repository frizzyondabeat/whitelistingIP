package com.frizzycode.whitelist.services;

import com.frizzycode.whitelist.model.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers(Pageable page);

    void addIpToWhitelist(String ip);

    void deleteIp(String ip);
}
