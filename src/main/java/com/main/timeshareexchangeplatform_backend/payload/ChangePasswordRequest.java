package com.main.timeshareexchangeplatform_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private int userid;
    private String passwordEntered;
    private String newPassword;
}
