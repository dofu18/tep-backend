package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationModel {
    private int destinationId;
    private String address;
    private String branch;
    private String city;
    private String country;
    private String description;
    private String desName;
}
