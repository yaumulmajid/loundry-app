package com.codeapps.loundry.module.user.seeder;

import com.codeapps.loundry.entity.Role;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
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
            add("role_su_admin");
        }
    }
    private void add(String roleName){
        Role role = new Role();
        role.setCreatedBy(0L);
        role.setName(roleName);

        roleRepository.save(role);
    }
}
