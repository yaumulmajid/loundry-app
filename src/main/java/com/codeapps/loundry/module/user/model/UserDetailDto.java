package com.codeapps.loundry.module.user.model;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDetailDto {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phone;
}
