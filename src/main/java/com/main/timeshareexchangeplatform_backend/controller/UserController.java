package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.reponse.ReponseObject;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;



    @PostMapping("/register")
    public User register (@RequestBody User user) {
        user.setRole("member");
        user.setStatus(true);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/login")
    public User login (@RequestBody User user) {
        User users = userRepository.findAllByUsernameAndPassword(user.getUsername(), user.getPassword());
        return users;
    }


    @GetMapping("/view-all-account")

    public List<UserDTO> findAll() {

        return userService.findAll();
    }
}
