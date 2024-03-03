package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.*;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.exception.ResourceNotFoundException;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "user added to system ";
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

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String changePassword(User user, String passwordEnter, String newPassword) {
        if (passwordEncoder.matches(passwordEnter, user.getPassword())) {
            userRepository.saveNewPassword(passwordEncoder.encode(newPassword), user.getUsername());
            return "Password has changed!";
        }
        return "Password has enter does not match with old password!";
    }

    @Override
    public User getReferenceById(UUID id) {
        return userRepository.getReferenceById(id);
    }

    //    @Override
//    public UserDTO getById(UUID userId){
//        Object result = userRepository.getUserById(userId);
//
//        // Chuyển đổi Object thành TimeshareRespone, bạn có thể thực hiện phần này theo cách bạn muốn
//        UserDTO userDTO = convertToObject(result);
//
//        return userDTO;
//    }
    private UserDTO convertToObject(Object result) {
        if (result == null) {
            return null;
        }

        Object[] row = (Object[]) result;

        // Extract các giá trị từ mảng row
        String user_id = (String) row[0];
        String user_name = (String) row[1];
        String password= (String) row[2];
        String fullname=(String) row[3];
        String email=(String) row[4];
        String phone=(String) row[5];
        String dob=(String) row[6];
        boolean gender = (boolean) row[7];
        boolean status = (boolean) row[8];
        String role=(String) row[9];


        // Tạo đối tượng TimeshareRespone và set giá trị
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(UUID.fromString(user_id));
        userDTO.setUser_name(user_name);
        userDTO.setFullname(fullname);
        userDTO.setPassword(password);
        userDTO.setEmail(email);
        userDTO.setPhone(phone);
        userDTO.setDob(dob);
        userDTO.setRole(role);
        userDTO.setGender(gender);
        userDTO.setStatus(status);
        return userDTO;
    }

}
