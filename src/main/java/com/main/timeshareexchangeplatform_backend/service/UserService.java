package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;

import java.util.List;

public interface UserService {
    User addUser(UserDTO userDTO);
    User login(LoginDTO userDTO);

    List<UserDTO> findAll();
}
