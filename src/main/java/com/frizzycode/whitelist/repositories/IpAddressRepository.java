package com.frizzycode.whitelist.repositories;

import com.frizzycode.whitelist.model.IpAddressObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddressObject, Long> {

    boolean existsByIpAddress(String userIp);

    Optional<IpAddressObject> findByIpAddress(String ipAddress);

    void deleteByIpAddress(String ip);
}
