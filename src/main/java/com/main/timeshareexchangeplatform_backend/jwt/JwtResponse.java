package com.main.timeshareexchangeplatform_backend.jwt;

import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.payload.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String refreshToken;
    private LoginResponse user;
}
