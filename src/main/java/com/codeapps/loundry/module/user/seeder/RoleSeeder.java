package com.codeapps.loundry.module.user.seeder;

import com.codeapps.loundry.module.user.entity.Role;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
@Slf4j
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        try {
            seed();
        } catch (Exception x ) {
            log.error(x.getMessage());
        }
    }
    private void seed(){
        if (roleRepository.count() == 0) {
            addRole("role_su_admin", "User Administrator");
        }
    }
    private void addRole(String roleName, String roleDescription){
        Role role = new Role();
        role.setCreatedBy(0L);
        role.setName(roleName);
        role.setDescription(roleDescription);
        roleRepository.save(role);
    }
}
