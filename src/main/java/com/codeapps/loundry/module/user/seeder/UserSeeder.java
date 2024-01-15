package com.codeapps.loundry.module.user.seeder;

import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
@Slf4j
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        try {
            seed();
        } catch (Exception x ) {
            log.error(x.getMessage());
        }
    }
    private void seed(){
        if (userRepository.count() == 0) {
            add("role_su_admin");
        }
    }
    private void add(String roleSuAdmin){
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setUsername("admin");
        userRequest.setPassword("password");
        userRequest.setEmail("admin@mail.co");
        userRequest.setPhone("0877-8889-9998");
        userRequest.setRole(roleSuAdmin);
        userRequest.setPassword("12345");
        userService.addUser(userRequest);
    }
}
