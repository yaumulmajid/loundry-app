package com.codeapps.loundry.module.user.model;


import com.codeapps.loundry.module.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class UserDetailDto {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Set<Role> role;
}
