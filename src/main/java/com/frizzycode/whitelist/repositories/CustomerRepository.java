package com.frizzycode.whitelist.repositories;

import com.frizzycode.whitelist.model.Customer;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c.id, c.firstName, c.lastName, c.email, c.gender from Customer c")
    @Override
    @NonNull
    Page<Customer> findAll(@NonNull Pageable pageable);
}
