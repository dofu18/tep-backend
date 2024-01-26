package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean checkPasswordByUsername(String username, String password);
    Boolean checkUserByUsername(String username);
    User getUserByUsername(String username);
}
