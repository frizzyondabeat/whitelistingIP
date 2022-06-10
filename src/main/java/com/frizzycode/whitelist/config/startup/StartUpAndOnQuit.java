package com.frizzycode.whitelist.config.startup;

import com.frizzycode.whitelist.model.Customer;
import com.frizzycode.whitelist.model.Gender;
import com.frizzycode.whitelist.model.IpAddressObject;
import com.frizzycode.whitelist.repositories.CustomerRepository;
import com.frizzycode.whitelist.repositories.IpAddressRepository;
import com.frizzycode.whitelist.config.security.CustomIpAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StartUpAndOnQuit {

    private final IpAddressRepository ipAddressRepository;
    private final CustomerRepository customerRepository;
    private final CustomIpAuthenticationProvider provider;

    @PostConstruct
    public void init(){
        customerRepository.saveAll(
                List.of(
                        Customer.builder()
                                .firstName("Parobo")
                                .lastName("Akpovero")
                                .email("payus.akpovero@gmail.com")
                                .gender(Gender.MALE)
                                .build(),
                        Customer.builder()
                                .firstName("Joshua")
                                .lastName("Omonigho")
                                .email("joshua.omonigho@outlook.com")
                                .gender(Gender.MALE)
                                .build()
                )
        );
    }

    @PreDestroy
    public void saveIps(){
        provider.getIpList().forEach(address -> {
            if (!ipAddressRepository.existsByIpAddress(address))
                ipAddressRepository.save(IpAddressObject.builder().ipAddress(address).build());
        });
    }
}
