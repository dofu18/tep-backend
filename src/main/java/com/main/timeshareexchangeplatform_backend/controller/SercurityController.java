package com.main.timeshareexchangeplatform_backend.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.RefreshToken;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.jwt.JwtResponse;
import com.main.timeshareexchangeplatform_backend.jwt.JwtUtility;
import com.main.timeshareexchangeplatform_backend.jwt.RefreshTokenRequest;
import com.main.timeshareexchangeplatform_backend.jwt.RefreshTokenResponse;
import com.main.timeshareexchangeplatform_backend.payload.ChangePasswordRequest;
import com.main.timeshareexchangeplatform_backend.payload.LoginRequest;
import com.main.timeshareexchangeplatform_backend.payload.LoginResponse;
import com.main.timeshareexchangeplatform_backend.payload.LogoutRequest;
import com.main.timeshareexchangeplatform_backend.repository.RefreshTokenRepository;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import com.main.timeshareexchangeplatform_backend.service.impl.RefreshTokenService;
import com.main.timeshareexchangeplatform_backend.service.impl.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("api/account")
public class SercurityController {
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserConverter converter;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    @PostMapping("/login")
    public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("");
        User userData = userService.findUserByUsername(userDetails.getUsername());
        LoginResponse data = new LoginResponse();
        data.setRole(role);
        data.setUser_name(userData.getUsername());
        data.setFull_name(userData.getFullname());
        data.setUserid(userData.getUser_id());
        data.setPhone(userData.getPhone());
        data.setDob(userData.getDob());
        data.setGender(userData.getGender());
        data.setEmail(userData.getEmail());


        String jwt = jwtUtility.generateToken(data);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), data));
    }

    @PostMapping("/signup")
    public String addCustomer(@RequestBody UserDTO userInfo) {
        userInfo.setRole("member");// customer roleId = 3
        userInfo.setStatus(true);
        userInfo.setCreateDate(LocalDate.now());
        return userService.addUser(converter.toEntity(userInfo));
    }

    @PostMapping("/signupAdmin")
    public String addAdmin(@RequestBody UserDTO userInfo) {
        userInfo.setRole("admin");// customer roleId = 3
        userInfo.setStatus(true);
        return userService.addUser(converter.toEntity(userInfo));
    }

    @PutMapping("/update-role/{id}")
    public ResponseEntity<String> updateRoleToAdmin(@PathVariable("id") UUID id) {
        String result = userService.UpdateRoleToAdmin(id);
        if (result.equals("Successfully changed user's role to admin")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("The user role is already admin")) {
            return ResponseEntity.badRequest().body(result);
        } else if (result.equals("No user found")) {
            return ResponseEntity.badRequest().body(result);
        }
        return null;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    // change by role name in db here the roleName is CUSTOMER
    public String sayHello() {
        return "Hello ADMIN";
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequest.getToken());
        User data = refreshTokenService.verifyExpiration(refreshToken).getUser();
        LoginResponse response = new LoginResponse();
        response.setUserid(data.getUser_id());
        response.setFull_name(data.getFullname());
        response.setUser_name(data.getUsername());
        response.setRole(data.getRole());
        response.setPhone(data.getPhone());
        response.setDob(data.getDob());
        response.setGender(data.getGender());
        String jwt = jwtUtility.generateToken(response);

        return ResponseEntity.ok(new RefreshTokenResponse(jwt, refreshToken.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest token) {
        refreshTokenService.removeFromInstance(refreshTokenRepository.findByToken(token.getToken()));
        return ResponseEntity.ok("Logout successful!");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = userService.getReferenceById(request.getUserid());
        String result = "Change password fail!";
        if (user != null) {
            result = userService.changePassword(user, request.getPasswordEntered(), request.getNewPassword());
        }
        return ResponseEntity.ok(result);
    }
}
