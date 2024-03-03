package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    String addUser(User userInfo);
    User login(LoginDTO userDTO);

    List<UserDTO> findAll();

    User findUserByUsername(String username);

    String changePassword(User user, String passwordEnter, String newPassword);
    User getReferenceById(UUID id);
//    UserDTO getById(UUID userId);


}
