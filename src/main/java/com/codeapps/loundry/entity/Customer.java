package com.codeapps.loundry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID customerId;

    @Column(name = "customer_code")
    private String codeId;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "mobile_phone_no")
    private String mobilePhone;
}
