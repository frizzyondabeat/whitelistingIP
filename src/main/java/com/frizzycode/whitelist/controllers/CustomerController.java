package com.frizzycode.whitelist.controllers;

import com.frizzycode.whitelist.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers/")
    public ResponseEntity<Object> getAllCustomers(@PageableDefault(sort = "id") Pageable page){
        try {
            log.info("Fetching all customers.....");
            return new ResponseEntity<>(customerService.getAllCustomers(page), HttpStatus.OK);
        } catch (Exception exception){
            log.error("Error fetching customers.\nError-Message:{}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("stocks/")
    public ResponseEntity<Object> getStocks(@PageableDefault Pageable pageable){
        try {
            log.info("Fetching all stocks...");
            return ResponseEntity.ok().body(customerService.getAllStocks(pageable));
        } catch (Exception exception){
            log.error("Error:{}", exception.getMessage());
            return new ResponseEntity<>("Error getting stocks",HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Object> addIpToWhitelist(@RequestParam String ipAddress){
        try {
            customerService.addIpToWhitelist(ipAddress);
            return new ResponseEntity<>("ip:" + ipAddress + " has been added to Whitelist successfully",HttpStatus.CREATED);
        } catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteIpFromWhiteList(@RequestParam String ipAddress){
        try {
            customerService.deleteIp(ipAddress);
            return new ResponseEntity<>("ip:" + ipAddress + " has been removed from Whitelist successfully",HttpStatus.OK);
        } catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
