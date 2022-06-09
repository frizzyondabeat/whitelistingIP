package com.frizzycode.whitelist.controllers;

import com.frizzycode.whitelist.model.Customer;
import com.frizzycode.whitelist.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer/")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable page
    ){
        try {
            log.info("Fetching all customers.....");
            return ResponseEntity.ok().body(customerService.getAllCustomers(page).getContent());
        } catch (Exception exception){
            log.error("Error fetching customers.\nError-Message:{}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
