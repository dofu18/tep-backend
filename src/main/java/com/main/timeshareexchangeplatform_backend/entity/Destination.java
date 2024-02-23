package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "destination")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID destination_id;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String name;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String branch;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String address;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String description;

    @Column(columnDefinition = "nvarchar(20) not null")
    private String city;

    @Column(columnDefinition = "nvarchar(20) not null")
    private String country;

    @OneToMany(mappedBy = "destination")
    private Collection<Timeshare> timeshares;

    @OneToMany(mappedBy = "destination")
//    @JoinColumn(name = "img_id")
    private Collection<Image> images;
}
