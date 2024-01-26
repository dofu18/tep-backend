package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class UserImplement implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean checkPasswordByUsername(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if(user.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public Boolean checkUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) return false;
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
