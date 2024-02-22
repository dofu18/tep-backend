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
    private UUID booking_id;
    private String bookingCode;
    private long total;
    private LocalDate success_date;
    private boolean status;
    private boolean payment_status;
    private int adults;
    private int children;
    private String telephone;
    private String fullname;
    private String country;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String payment_method;
    private UUID user_id;
    private UUID timeshare_id;
}
