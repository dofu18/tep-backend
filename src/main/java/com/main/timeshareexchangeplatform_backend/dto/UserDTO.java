package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String user_name;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String dob;
    private Boolean gender;
    private boolean status;
}
