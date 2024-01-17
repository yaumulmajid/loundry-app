package com.codeapps.loundry.module.customer.controller;

import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.customer.model.CustomerDto;
import com.codeapps.loundry.module.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIDataResponseDTO> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomer());
    }

    @GetMapping("/{codeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIDataResponseDTO> getCustomerByCodeId(@PathVariable String codeId) {
        return ResponseEntity.ok(customerService.getCustomerByCode(codeId));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIDataResponseDTO> creteCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIDataResponseDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId,customerDto));
    }
}
