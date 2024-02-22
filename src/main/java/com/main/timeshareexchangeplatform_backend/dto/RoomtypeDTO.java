package com.main.timeshareexchangeplatform_backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomtypeDTO {

    private UUID RoomtypeId;
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
