package com.frizzycode.whitelist.services;

import com.frizzycode.whitelist.exceptions.IpNotFoundException;
import com.frizzycode.whitelist.model.Customer;
import com.frizzycode.whitelist.model.Stock;
import com.frizzycode.whitelist.repositories.CustomerRepository;
import com.frizzycode.whitelist.config.security.CustomIpAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomIpAuthenticationProvider provider;

    @Override
    public List<Customer> getAllCustomers(Pageable page) {
        return customerRepository.findAll(page).getContent();
    }

    @Override
    public void addIpToWhitelist(String ip) {
        provider.addToWhitelist(ip);
    }

    @Override
    public void deleteIp(String ip) {
        if (provider.existsInWhitelist(ip))
            provider.removeFromWhitelist(ip);
        else
            throw new IpNotFoundException("No such whitelisted IP");
    }

    @Override
    public List<Stock> getAllStocks(Pageable pageable) {
        RestTemplate template = new RestTemplateBuilder()
                .basicAuthentication("frizzyondabeat", "tomriddle")
                .build();
        return template.exchange(
                        "http://localhost:5440/api/stocks/",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Stock>>() {},
                pageable
                )
                .getBody();
    }
}
