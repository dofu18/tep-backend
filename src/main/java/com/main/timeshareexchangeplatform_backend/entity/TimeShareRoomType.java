package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timeshare_roomtype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeShareRoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "timeshare")
    private Timeshare timeshare;

    @ManyToOne
    @JoinColumn(name = "roomtype")
    private Roomtype roomtype;
}
