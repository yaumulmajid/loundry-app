package com.codeapps.loundry.module.user.controller;


import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('role_su_admin')")
    public ResponseEntity<APIDataResponseDTO> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('role_su_admin')")
    public ResponseEntity<APIDataResponseDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('role_su_admin')")
    public ResponseEntity<APIDataResponseDTO> creteUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('role_su_admin')")
    public ResponseEntity<APIDataResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(userId,userRequestDto));
    }
}
