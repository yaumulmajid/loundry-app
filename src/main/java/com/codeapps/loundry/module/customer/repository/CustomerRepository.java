package com.codeapps.loundry.module.customer.repository;

import com.codeapps.loundry.module.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCodeId (String codeId);
    Customer findByCustomerId (Long customerId);
}
