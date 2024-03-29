package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private UUID transaction_id;
    private long transaction_fee;
    private LocalDateTime transaction_time;
    private UUID service_id;
    private UUID user_id;
    private String transaction_code;
    private LocalDateTime expireDate;


}
