package com.codeapps.loundry.module.customer.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {
    private Long customerId;
    private String code;
    private String name;
    private String mobilePhone;
}
