package com.codeapps.loundry.module.user.service.Impl;

import com.codeapps.loundry.exceptions.NotFoundException;
import com.codeapps.loundry.exceptions.RegisterFailedException;
import com.codeapps.loundry.module.user.entity.Role;
import com.codeapps.loundry.module.user.entity.User;
import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.RoleDetailDto;
import com.codeapps.loundry.module.user.model.UserDetailDto;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

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
    @Override
    public APIDataResponseDTO updateUser(Long userId, UserRequestDto request) {
        try {
            User upadte = udateUserEntity(userId,request);
            APIDataResponseDTO apiDataResponseDTO = new APIDataResponseDTO();
            apiDataResponseDTO.setSuccess(true);
            apiDataResponseDTO.setData(upadte);
            return apiDataResponseDTO;
        } catch (Exception ex) {
            return new APIDataResponseDTO(true,ex.getMessage());
        }
    }
    @Override
    public APIDataResponseDTO getUsers() {
        try {
            return new APIDataResponseDTO(true, getAllUser());
        }catch (Exception ex) {
            return new APIDataResponseDTO(true, ex.getMessage());
        }
    }

    @Override
    public APIDataResponseDTO getUserById(Long userId) {
        try {
            return new APIDataResponseDTO(true, getUserId(userId));
        }catch (Exception ex) {
            return new APIDataResponseDTO(true, ex.getMessage());
        }
    }

    private User createUserEntity(UserRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()) != null ) {
            throw new RegisterFailedException("Username Telah Terdaftar");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        Role role = roleRepository.findByName(request.getRole());
        if (role != null) {
            user.setRole(Collections.singleton(role));
        } else {
            throw new NotFoundException("Role not found: " + request.getRole());
        }
        user.setCreatedBy(1L);
        return userRepository.save(user);
    }
    private User udateUserEntity(Long userId, UserRequestDto request) {
        User user = userRepository.findById(userId).get();
        if (user == null){
            throw new NotFoundException("Customer Not Found");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findByName(request.getRole());
        if (role != null) {
            user.setRole(Collections.singleton(role));
        } else {
            throw new NotFoundException("Role not found: " + request.getRole());
        }
        user.setModifiedBy(1L);
        userRepository.save(user);
        return user;
    }
    private List<UserDetailDto> getAllUser(){
        List<User> userList = userRepository.findAll();
        List<UserDetailDto> userDetailDtos = new ArrayList<>();
        for (User user : userList){
            UserDetailDto obj = new UserDetailDto();
            obj.setUsername(user.getUsername());
            obj.setEmail(user.getEmail());
            obj.setPhone(user.getPhone());
            obj.setPassword(passwordEncoder.encode(user.getPassword()));
            List<RoleDetailDto> roleDtos = user.getRole().stream()
                    .map(role -> new RoleDetailDto(role.getName(), role.getDescription()))
                    .collect(Collectors.toList());
            obj.setRole(roleDtos);
            userDetailDtos.add(obj);
        }
        return userDetailDtos;
    }
    private UserDetailDto getUserId(Long userId){
        User user = userRepository.findById(userId).get();
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUsername(user.getUsername());
        userDetailDto.setEmail(user.getEmail());
        userDetailDto.setPhone(user.getPhone());
        userDetailDto.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleDetailDto> roleDtos = user.getRole().stream()
                .map(role -> new RoleDetailDto(role.getName(), role.getDescription()))
                .collect(Collectors.toList());
        userDetailDto.setRole(roleDtos);
        return userDetailDto;
    }
}
