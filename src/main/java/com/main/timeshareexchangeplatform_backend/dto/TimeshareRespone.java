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

    UUID timeshare_id;
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
    UUID destination_id;
    UUID post_by;
    DestinationDTO des;

    RoomtypeDTO room;
}
