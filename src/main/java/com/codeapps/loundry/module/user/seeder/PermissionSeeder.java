package com.codeapps.loundry.module.user.seeder;

import com.codeapps.loundry.entity.Permission;
import com.codeapps.loundry.module.user.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
@Slf4j
@RequiredArgsConstructor
public class PermissionSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    @Override
    public void run(String... args) throws Exception {
        try {
            seed();
        } catch (Exception x ) {
            log.error(x.getMessage());
        }

    }
    private void seed() {
        if (permissionRepository.count() == 0) {
            add("role_su_admin");
        }
    }
    private void add(String permissionName) {
        Permission permission = new Permission();
        permission.setName(permissionName);
        permission.setCreatedBy(0L);

        permissionRepository.save(permission);
    }
}
