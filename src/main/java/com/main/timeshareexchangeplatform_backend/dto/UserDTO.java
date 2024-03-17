package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID user_id;
    private String user_name;
    private String fullname;
    private String email;
    private LocalDate createDate;
    private String phone;
    private String dob;
    private Boolean gender;
    private boolean status;
    private String role;

}
