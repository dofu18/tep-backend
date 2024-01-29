package com.main.timeshareexchangeplatform_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomtypeDTO {
    private int RoomtypeId;
    private int bath;
    private int bed;
    private int sleeps;
    private String Entertaiment;
    private String Feature;
    private String kitchen;
    private String name;
    private String Policies;
    private String roomview;
}
