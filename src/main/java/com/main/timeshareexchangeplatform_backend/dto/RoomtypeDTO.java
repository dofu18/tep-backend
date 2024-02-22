package com.main.timeshareexchangeplatform_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomtypeDTO {
    private int roomtypeId;
    private int bath;
    private int bed;
    private int sleeps;
    private String entertaiment;
    private String feature;
    private String kitchen;
    private String name;
    private String policies;
    private String roomview;
}
