package com.main.timeshareexchangeplatform_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private UUID userid;
    private String user_name;
    private String role;
    private String full_name;
    private LocalDate dob;
    private Boolean gender;
    private String phone;
    private String email;

}
