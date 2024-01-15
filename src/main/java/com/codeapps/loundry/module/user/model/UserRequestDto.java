package com.codeapps.loundry.module.user.model;

import com.codeapps.loundry.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRequestDto {
    private String username;

    private String password;

    private String email;

    private String phone;
    private String role;
}
