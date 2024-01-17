package com.codeapps.loundry.module.user.controller;


import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIDataResponseDTO> creteCustomer(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }
}
