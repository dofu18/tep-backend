package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModelResponse {
    private LocalDate create_date;
    private int status;
    private UUID request_id;
    private UserDTO response_by;
    private UserDTO request_by;
    private ResponseTimeshare timeshare_id;
    private String message;
}
