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
    private String timeshareName;
    private String description;
    private boolean status;
    private float price;
    private int nights;
    private UserModel postBy;
    private DestinationModel destinationModel;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private boolean exchange;
}
