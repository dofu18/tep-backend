package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;

public interface UserService {
    User addUser(UserDTO userDTO);
    User login(LoginDTO userDTO);
}
