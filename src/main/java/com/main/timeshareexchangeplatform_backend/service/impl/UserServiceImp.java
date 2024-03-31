package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.*;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
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
    PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "user added to system ";
    }

//    @Override
//    public User login(LoginDTO userDTO) {
//        return userRepository.findUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword())
//                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
//    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTO = userRepository.findAll().stream().map(userConverter::toDTO)
                .collect(Collectors.toList());

        return userDTO;
    }

    @Override
    public List<UserDTO> getAccountBanned() {
        List<User> user = userRepository.getAccountBanned();
        return userConverter.convertToUserDTOList(user);
    }

    @Override
    public List<UserDTO> getAccountActive() {
        List<User> user = userRepository.getAccountActive();
        return userConverter.convertToUserDTOList(user);
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


    @Override
    public String UpdateRoleToAdmin(UUID id) {
        User user = userRepository.findUserByID(id);

        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                return "The user role is already admin";
            } else {
                user.setRole("admin");
                userRepository.save(user);
                return "Successfully changed user's role to admin";
            }
        }

        return "No user found";
    }

    @Override
    public List<UserDTO> getUsersCreatedWithinLast30Days() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        List<User> timeshares = userRepository.findUsersCreatedWithinLast30Days(startDate, endDate);
        return userConverter.convertToUserDTOList(timeshares);
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
        String userId = (String) row[0];
        Date dob=(Date) row[1];
        String email = (String) row[2];
        String fullName = (String) row[3];
        Boolean gender=(Boolean) row[4];
        String password = (String) row[5];

        String phone= (String) row[6] ;
        String role=(String) row[7];
        String userName=(String) row[9];
        Boolean status= (Boolean) row[8];




        // Tạo đối tượng TimeshareRespone và set giá trị
        UserDTO userDTO=new UserDTO();
        userDTO.setUser_id(UUID.fromString(userId));
        userDTO.setUser_name(userName);
        userDTO.setFullname(fullName);
        userDTO.setEmail(email);
        userDTO.setDob(String.valueOf(dob));
        userDTO.setPhone(phone);
        userDTO.setRole(role);
        userDTO.setStatus(status);
        userDTO.setGender(gender);
        return userDTO;
    }


}
