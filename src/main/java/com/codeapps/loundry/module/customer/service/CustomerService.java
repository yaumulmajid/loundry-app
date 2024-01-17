package com.codeapps.loundry.module.customer.service;

import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.customer.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    APIDataResponseDTO getCustomer();
    APIDataResponseDTO getCustomerByCode(String codeId);
    APIDataResponseDTO createCustomer(CustomerDto customerDto);
    APIDataResponseDTO updateCustomer(Long customerId, CustomerDto customerDto);

}
