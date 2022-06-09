package com.frizzycode.whitelist.services;

import com.frizzycode.whitelist.model.Customer;
import com.frizzycode.whitelist.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Page<Customer> getAllCustomers(Pageable page) {
        return customerRepository.findAll(page);
    }
}
