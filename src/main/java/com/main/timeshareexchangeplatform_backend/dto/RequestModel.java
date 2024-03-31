package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {
    private LocalDate create_date;
    private int status;
    private UUID request_id;
    private UUID response_by;
    private UUID request_by;
    private UUID timeshare_request_id;
    private UUID timeshare_response_id;
    private String message;
    private String requestCode;
}
