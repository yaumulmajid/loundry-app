package com.codeapps.loundry.module.customer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CustomerDto {
    private UUID customerId;
    private String codeId;
    private String name;
    private String mobilePhone;
}
