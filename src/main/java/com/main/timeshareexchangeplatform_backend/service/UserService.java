package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User addUser(UserDTO userDTO);
    User login(LoginDTO userDTO);

    List<UserDTO> findAll();

<<<<<<< HEAD
    UserDTO getById(UUID userId);
=======
//    UserDTO addUser(UserDTO userInfo);

>>>>>>> f5d36451659e28c500079a8dbeb2e9354f285ac9
}
