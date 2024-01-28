package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingModel {
    private int booking_id;
    private String booking_number;
    private float total;
    private LocalDate create_date;
    private LocalDate success_date;
    private boolean status;
    private int user_id;
    private int timeshare_id;
}
