package com.codeapps.loundry.module.user.repository;

import com.codeapps.loundry.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleEntityByName(String name);
}
