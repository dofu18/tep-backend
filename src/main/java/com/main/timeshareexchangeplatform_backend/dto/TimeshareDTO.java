package com.main.timeshareexchangeplatform_backend.dto;

import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeshareDTO {
    private UUID timeshare_id;
    private LocalDate date_start;
    private LocalDate date_end;
    private int nights;
    private long price;
    private boolean status;
    private String timeshare_code;
    private String name;
    private UUID  owner;
    private UUID destination_id;
    private String description;
    private String image_url;
    private String city;
    private boolean exchange;
    private LocalDate create_date;
    private UUID tempOwner;
}
