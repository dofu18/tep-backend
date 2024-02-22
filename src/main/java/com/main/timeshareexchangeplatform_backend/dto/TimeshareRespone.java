package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeshareRespone {
    int timeshare_id;
    Date date_end;
    Date date_start;
    String description;
    boolean exchance;
    String image_url;
    String name;
    int nights;
    long price;
    boolean status;
    DestinationDTO des;
    RoomtypeDTO room;
    String post_by;


}
