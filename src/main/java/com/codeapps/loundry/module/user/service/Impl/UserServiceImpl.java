package com.codeapps.loundry.module.user.service.Impl;

import com.codeapps.loundry.exceptions.NotFoundException;
import com.codeapps.loundry.module.customer.entity.Customer;
import com.codeapps.loundry.module.user.entity.Role;
import com.codeapps.loundry.module.user.entity.User;
import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.UserDetailDto;
import com.codeapps.loundry.module.user.model.UserRequestDto;
import com.codeapps.loundry.module.user.repository.RoleRepository;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        Role role = roleRepository.findByName(request.getRole());
        if (role != null) {
            user.setRole(Collections.singleton(role));
        } else {
            throw new NotFoundException("Role not found: " + request.getRole());
        }
        user.setCreatedBy(0L);
        return userRepository.save(user);
    }
    private User udateUserEntity(Long userId, UserRequestDto request) {
        User user = userRepository.findById(userId).get();
        if (user == null){
            throw new NotFoundException("Customer Not Found");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Role role = roleRepository.findByName(request.getRole());
        if (role != null) {
            user.setRole(Collections.singleton(role));
        } else {
            throw new NotFoundException("Role not found: " + request.getRole());
        }
        userRepository.save(user);
        return user;
    }
    private List<UserDetailDto> getAllUser(){
        List<User> userList = userRepository.findAll();
        List<UserDetailDto> userDetailDtos = new ArrayList<>();
        for (User user : userList){
            UserDetailDto obj = new UserDetailDto();
            obj.setUserId(user.getId());
            obj.setUsername(user.getUsername());
            obj.setEmail(user.getEmail());
            obj.setPhone(user.getPhone());
            obj.setPassword(user.getPassword());
            userDetailDtos.add(obj);
        }
        return userDetailDtos;
    }
    private UserDetailDto getUserId(Long userId){
        User user = userRepository.findById(userId).get();
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUserId(user.getId());
        userDetailDto.setUsername(user.getUsername());
        userDetailDto.setEmail(user.getEmail());
        userDetailDto.setPhone(user.getPhone());
        userDetailDto.setPassword(user.getPassword());
        return userDetailDto;
    }
}
