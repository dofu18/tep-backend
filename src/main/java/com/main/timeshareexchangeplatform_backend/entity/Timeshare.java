package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "timeshare")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeshare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeshare_id;

    @Column(columnDefinition = "varchar(50)")
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

    @Column (columnDefinition = "varchar(max)")
    private String description;

    @Column(columnDefinition = "varchar(max) not null")
    private String image_url;

    @OneToOne(mappedBy = "timeshare")
    private Booking booking;

    @OneToMany(mappedBy = "timeshare")
    private Collection<Request> requests;

    @OneToOne(mappedBy = "timeshare")
//    @JoinColumn(name = "")
    private Roomtype roomtype;

    @ManyToOne
    @JoinColumn(name = "post_by")
    private User user;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

//    @OneToOne (mappedBy = "timeshare")
//    private Exchange_information exchangeInformation;

//    @OneToOne(mappedBy = "response")
//    private Request request;
}