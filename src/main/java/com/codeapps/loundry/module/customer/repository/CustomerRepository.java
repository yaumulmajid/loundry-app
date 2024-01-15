package com.codeapps.loundry.module.customer.repository;

import com.codeapps.loundry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByCodeId (String codeId);
    Customer findByCustomerId (UUID customerId);
}
