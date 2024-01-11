package com.codeapps.loundry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customerId;

    @Column(name = "customer_code")
    private String codeId;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "mobile_phone_no")
    private String mobilePhone;
}
