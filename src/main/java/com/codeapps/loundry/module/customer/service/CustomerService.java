package com.codeapps.loundry.module.customer.service;

import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.customer.model.CustomerDto;


public interface CustomerService {
    APIDataResponseDTO getCustomer();
    APIDataResponseDTO getCustomerByCode(String codeId);
    APIDataResponseDTO createCustomer(CustomerDto customerDto);
    APIDataResponseDTO updateCustomer(Long customerId, CustomerDto customerDto);

}
