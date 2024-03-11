package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTimeshare {
    private UUID timeshareId;
    private String timeshareCode;
    private String timeshareName;
    private String description;
    private boolean status;
    private long price;
    private int nights;
    private UserDTO postBy;
    private DestinationModel destinationModel;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private boolean exchange;
    private String city;
    private String image_url;
    private LocalDate create_date;
}
