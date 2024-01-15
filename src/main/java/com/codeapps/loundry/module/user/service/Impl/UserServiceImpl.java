package com.codeapps.loundry.module.user.service.Impl;

import com.codeapps.loundry.entity.Role;
import com.codeapps.loundry.entity.User;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    @Override
    public void addUser(UserRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        Role role = roleRepository.findRoleEntityByName(request.getRole());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRole(roles);
        user.setCreatedBy(0L);
        userRepository.save(user);
    }
}
