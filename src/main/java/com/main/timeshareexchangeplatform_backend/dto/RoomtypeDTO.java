package com.main.timeshareexchangeplatform_backend.dto;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomtypeDTO {

    private String RoomtypeId;
    private int bath;
    private int bed;
    private int sleeps;
    private String entertaiment;
    private String feature;
    private String kitchen;
    private String name;
    private String policies;
    private String roomview;
    private TimeshareDTO timeshareId;
}
