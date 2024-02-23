package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeshareRespone {

    String timeshare_id;
    String address;
    Date date_end;
    Date date_start;
    String description;
    boolean exchance;
    String image_url;
    String name;
    int nights;
    long price;
    boolean status;

    String post_by;
    DestinationDTO des;
    RoomtypeDTO room;
}
