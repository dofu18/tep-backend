package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationModel {
    private UUID destinationId;
    private String address;
    private String branch;
    private String city;
    private String country;
    private String description;
    private String desName;
}
