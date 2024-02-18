package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.exception.ResourceNotFoundException;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(UserDTO userDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(userDTO.getDob(), formatter);

        return userRepository.save(User.builder()
                .user_name(userDTO.getUser_name())
                .fullname(userDTO.getFullname())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .dob(localDate)
                .phone(userDTO.getPhone())
                .gender(userDTO.getGender())
                .status(userDTO.isStatus())
                .build());
    }

    @Override
    public User login(LoginDTO userDTO) {
        return userRepository.findUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
