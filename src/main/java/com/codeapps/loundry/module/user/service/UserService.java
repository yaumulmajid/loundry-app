package com.codeapps.loundry.module.user.service;

import com.codeapps.loundry.model.APIDataResponseDTO;
import com.codeapps.loundry.module.user.entity.User;
import com.codeapps.loundry.module.user.model.UserRequestDto;

public interface UserService {
    APIDataResponseDTO createUser(UserRequestDto request);

    User addUser (UserRequestDto userRequestDto);
}
