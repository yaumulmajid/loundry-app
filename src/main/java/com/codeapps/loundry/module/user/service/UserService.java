package com.codeapps.loundry.module.user.service;

import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.model.UserRequestDto;


public interface UserService {
    APIDataResponseDTO createUser(UserRequestDto request);
    APIDataResponseDTO updateUser(Long userId,UserRequestDto request);
    APIDataResponseDTO getUsers();
    APIDataResponseDTO getUserById(Long userId);
}
