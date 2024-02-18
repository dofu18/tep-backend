package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roomtype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roomtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomtype_id;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column
    private Integer sleeps;

    @Column(columnDefinition = "nvarchar(max)")
    private String room_view;

    @Column
    private Integer bed;

    @Column
    private Integer bath;

    @Column(columnDefinition = "nvarchar(max)")
    private String kitchen;

    @Column(columnDefinition = "nvarchar(max)")
    private String entertainment;

    @Column(columnDefinition = "nvarchar(max)")
    private String features;

    @Column(columnDefinition = "nvarchar(max)")
    private String policies;

    @OneToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;

}
