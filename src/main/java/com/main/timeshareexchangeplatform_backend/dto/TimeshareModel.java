package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeshareModel {
    private UUID timeshare_id;
    private LocalDate date_start;
    private LocalDate date_end;
    private boolean exchange;
    private int nights;
    private boolean status;
    private long price;
    private String description;
    private String image_url;
    private String name;
    private UUID destination_id;
    private UUID post_by;
}
