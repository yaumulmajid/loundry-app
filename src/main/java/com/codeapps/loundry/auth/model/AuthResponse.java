package com.codeapps.loundry.auth.model;

import com.codeapps.loundry.module.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private User user;
    private String token;

    public AuthResponse(User user, String newGenerateToken) {
    }
}
