package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.LoginDTO;

import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.reponse.ReponseObject;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserConverter userConverter;

//    @PostMapping("/register")
//    public ResponseEntity<User> create(@RequestBody UserDTO userDTO){
//
//        return ResponseEntity.ok(userService.addUser(userDTO));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<ReponseObject> login(@RequestBody LoginDTO userDTO){
//        return ResponseEntity.ok(
//                ReponseObject.builder()
//                        .code(HttpStatus.OK.value())
//                        .message("Success")
//                        .data(userService.login(userDTO))
//                        .build()
//        );
//    }

    @GetMapping("/view-all-account")

    public List<UserDTO> findAll() {

        return userService.findAll();
    }

    @GetMapping("/last30days")
    public List<UserDTO> getTimesharesCreatedWithinLast30Days() {
        return userService.getUsersCreatedWithinLast30Days();
    }

    @GetMapping("/details/{userid}")

    public UserDTO userDetails(@PathVariable UUID userid) {
        UserDTO userDTO;
        userDTO = userConverter.toDTO(userService.getReferenceById(userid));

        return userDTO;
    }

//    @GetMapping("/getUserById/{userId}")
//    public ResponseEntity<?> getUserById (@PathVariable UUID userId){
//        UserDTO userDTO= userService.getUserById(userId);
//
//        if (userDTO != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found for userId: " + userId);
//        }
//    }
}
