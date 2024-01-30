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
    String address;
    Date date_end;
    Date date_start;
    String description;
    boolean exchance;
    int nights;
    double price;
    boolean status;
    int destination_id;
    int post_by;
    String address1;
    String branch;
    String city;
    String country;
    String description1;
    String name;
    int bath;
    String entertainment;
    String features;
    String kitchen;
    String name2;
    String policies;
    String room_view;
    int sleeps;
}
