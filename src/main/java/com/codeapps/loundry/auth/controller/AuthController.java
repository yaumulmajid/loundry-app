package com.codeapps.loundry.auth.controller;

import com.codeapps.loundry.auth.model.AuthRequest;
import com.codeapps.loundry.auth.model.AuthResponse;
import com.codeapps.loundry.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> createToken(@ModelAttribute AuthRequest request) throws Exception {
        return ResponseEntity.ok(authService.createToken(request));
    }
}
