package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "timeshare")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeshare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID timeshare_id;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate date_start;

    @Column(columnDefinition = "DATE")
    private LocalDate date_end;

    @Column
    private int nights;

    @Column(nullable = false)
    private long price;

    @Column
    private boolean status;

    @Column
    private Boolean exchange;

    @Column (columnDefinition = "nvarchar(max)")
    private String description;

    @Column (columnDefinition = "nvarchar(max)")
    private String city;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String image_url;

    @OneToOne(mappedBy = "timeshare")
    private Booking booking;

    @OneToMany(mappedBy = "timeshare_request")
    private Collection<Request> requests;

    @OneToMany(mappedBy = "timeshare_response")
    private Collection<Request> response;

    @OneToOne(mappedBy = "timeshare")
//    @JoinColumn(name = "")
    private Roomtype roomtype;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User postBy;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

//    @OneToOne (mappedBy = "timeshare")
//    private Exchange_information exchangeInformation;

//    @OneToOne(mappedBy = "response")
//    private Request request;
}