package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDTO {
    private UUID destinationId;
    private String address;
    private String branch;
    private String city;
    private String Country;
    private String description;
    private String name;
}
