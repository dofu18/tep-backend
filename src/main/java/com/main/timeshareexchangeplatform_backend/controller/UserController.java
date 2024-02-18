package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.reponse.ReponseObject;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO){

        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ReponseObject> login(@RequestBody LoginDTO userDTO){
        return ResponseEntity.ok(
                ReponseObject.builder()
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .data(userService.login(userDTO))
                        .build()
        );
    }
}
