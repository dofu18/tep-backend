package com.main.timeshareexchangeplatform_backend.dto;

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
    private String name;
    private UUID post_by;
    private UUID destination_id;
    private String description;
}
