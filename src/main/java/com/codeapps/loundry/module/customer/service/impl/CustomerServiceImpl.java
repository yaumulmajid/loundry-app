package com.codeapps.loundry.module.customer.service.impl;

import com.codeapps.loundry.entity.Customer;
import com.codeapps.loundry.exceptions.NotFoundException;
import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.customer.model.CustomerDto;
import com.codeapps.loundry.module.customer.repository.CustomerRepository;
import com.codeapps.loundry.module.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;

    @Override
    public APIDataResponseDTO getCustomer() {
        try {
            return new APIDataResponseDTO(true, getCustomers());
        }catch (Exception ex) {
            return new APIDataResponseDTO(true, ex.getMessage());
        }
    }

    @Override
    public APIDataResponseDTO getCustomerByCode(String codeId) {
        try {
            return new APIDataResponseDTO(true, toDTO(getCustomerCode(codeId)));
        } catch (Exception ex) {
            return new APIDataResponseDTO(true,ex.getMessage());
        }
    }

    @Override
    public APIDataResponseDTO createCustomer(CustomerDto customerDto) {
        try {
            Customer insert = createCustomerEntity(customerDto);

            APIDataResponseDTO apiDataResponseDTO = new APIDataResponseDTO();
            apiDataResponseDTO.setSuccess(true);
            apiDataResponseDTO.setMessageCodes(null);
            apiDataResponseDTO.setData(insert);
            return apiDataResponseDTO;

        } catch (Exception ex) {
            return new APIDataResponseDTO(true,ex.getMessage());
        }
    }

    @Override
    public APIDataResponseDTO updateCustomer(UUID customerId, CustomerDto customerDto) {
        Customer update = updateCustomerEntity(customerId,customerDto);
        APIDataResponseDTO apiDataResponseDTO = new APIDataResponseDTO();
        apiDataResponseDTO.setSuccess(true);
        apiDataResponseDTO.setMessageCodes(null);
        apiDataResponseDTO.setData(update);
        return apiDataResponseDTO;
    }

    private Customer createCustomerEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCodeId(customerDto.getCodeId());
        customer.setName(customerDto.getName());
        customer.setMobilePhone(customerDto.getMobilePhone());
        customerRepository.save(customer);
        return customer;
    }

    private Customer updateCustomerEntity(UUID customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        customer.setCodeId(customerDto.getCodeId());
        customer.setName(customerDto.getName());
        customer.setMobilePhone(customerDto.getMobilePhone());
        customerRepository.save(customer);
        return customer;
    }
    private List<CustomerDto> getCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerList){
            CustomerDto obj = new CustomerDto();
            obj.setCustomerId(customer.getCustomerId());
            obj.setCodeId(customer.getCodeId());
            obj.setName(customer.getName());
            obj.setMobilePhone(customer.getMobilePhone());
            customerDtos.add(obj);
        }
        return customerDtos;
    }
    private Customer getCustomerCode(String codeId){
        Customer customer = customerRepository.findByCodeId(codeId);
        if (customer ==null){
            throw new NotFoundException("Customer Not Found");
        }
        return customer;
    }
    private CustomerDto toDTO(Customer obj){
        CustomerDto customerDto = mapper.convertValue(obj, CustomerDto.class);
        return customerDto;
    }
}
