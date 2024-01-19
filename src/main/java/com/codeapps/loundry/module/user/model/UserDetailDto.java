package com.codeapps.loundry.module.user.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDetailDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private List<RoleDetailDto> role;
}
