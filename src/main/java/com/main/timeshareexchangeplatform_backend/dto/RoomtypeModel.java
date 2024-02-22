package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomtypeModel {
    private int RoomtypeId;
    private int bath;
    private int bed;
    private String entertaiment;
    private String features;
    private int kitchen;
    private String name;
    private String policies;
    private String room_view;
    private int sleeps;
}
