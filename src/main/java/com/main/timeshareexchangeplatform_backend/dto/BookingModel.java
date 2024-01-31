package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingModel {
    private int booking_id;
    private String bookingCode;
    private long total;
    private LocalDate create_date;
    private LocalDate success_date;
    private boolean status;
    private UUID user_id;
    private int timeshare_id;
}
