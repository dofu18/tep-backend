package com.main.timeshareexchangeplatform_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private UUID userid;
    private String passwordEntered;
    private String newPassword;
}
