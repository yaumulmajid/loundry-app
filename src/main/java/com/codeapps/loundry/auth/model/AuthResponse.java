package com.codeapps.loundry.auth.model;

import com.codeapps.loundry.module.user.model.UserDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private UserDetailDto user;
    private String token;
}
