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

    @Column(columnDefinition = "DATE")
    private LocalDate date_start;

    @Column(columnDefinition = "DATE")
    private LocalDate date_end;

    @Column
    private int nights;

    @Column
    private Double price;

    @Column
    private boolean status;

    @Column(columnDefinition = "varchar(100) not null")
    private String address;

    @OneToOne(mappedBy = "timeshare")
    private Order_Detail order_detail;

    @OneToMany(mappedBy = "timeshare")
    private Collection<Request> requests;

    @OneToMany(mappedBy = "timeshare")
    private Collection<Roomtype> roomtypes;

    @ManyToOne
    @JoinColumn(name = "post_by")
    private User user;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

}
