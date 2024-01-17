package com.codeapps.loundry.module.user.service.Impl;

import com.codeapps.loundry.module.user.entity.Role;
import com.codeapps.loundry.module.user.entity.User;
import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public APIDataResponseDTO createUser(UserRequestDto request) {
        try {
            User insert = createUserEntity(request);

            APIDataResponseDTO apiDataResponseDTO = new APIDataResponseDTO();
            apiDataResponseDTO.setSuccess(true);

            apiDataResponseDTO.setData(insert);
            return apiDataResponseDTO;

        } catch (Exception ex) {
            return new APIDataResponseDTO(true,ex.getMessage());
        }
    }

    private User createUserEntity(UserRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        Role role = roleRepository.findByName(request.getRole());
        user.setRole(Collections.singleton(role));
        user.setCreatedBy(0L);
        userRepository.save(user);
        return user;
    }

    @Override
    public User addUser(UserRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        Role role = roleRepository.findByName(request.getRole());
        user.setRole(Collections.singleton(role));
        user.setCreatedBy(0L);
        userRepository.save(user);
        return user;
    }
}
