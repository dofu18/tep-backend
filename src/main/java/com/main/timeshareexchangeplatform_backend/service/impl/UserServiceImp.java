package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.exception.ResourceNotFoundException;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;

    @Override
    public User addUser(UserDTO userDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(userDTO.getDob(), formatter);

        return userRepository.save(User.builder()
                .username(userDTO.getUser_name())
                .fullname(userDTO.getFullname())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .dob(localDate)
                .phone(userDTO.getPhone())
                .gender(userDTO.getGender())
                .status(userDTO.isStatus())
                .role(userDTO.getRole())
                .build());
    }

    @Override
    public User login(LoginDTO userDTO) {
        return userRepository.findUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTO = userRepository.findAll().stream().map(userConverter::toDTO)
                .collect(Collectors.toList());

        return userDTO;
    }

//    @Override
//    public UserDTO addUser(UserDTO userInfo) {
//        User user = userConverter.toEntity(userInfo);
//        userInfo.setPassword(userInfo.getPassword());
//        userRepository.save(user);
//        return userInfo;
//    }
}
