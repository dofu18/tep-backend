package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID booking_id;

    @Column(unique = true, columnDefinition = "varchar(50)", name = "booking_code", nullable = false)
    private String bookingCode;

    @Column (nullable = false)
    private long total;

    @Column
    private boolean payment_status;

    @Column(columnDefinition = "DATE")
    private LocalDate success_date;

    @Column
    private Boolean status;

    @Column
    private int adults;

    @Column
    private int children;

    @Column (columnDefinition = "varchar(15) not null")
    private String telephone;

    @Column (columnDefinition = "varchar(50)")
    private String fullname;

    @Column (columnDefinition = "varchar(50) not null")
    private String country;

    @Column (columnDefinition = "varchar(50) not null")
    private String street;

    @Column (columnDefinition = "varchar(50) not null")
    private String city;

    @Column (columnDefinition = "varchar(50) not null")
    private String state;

    @Column (columnDefinition = "varchar(20) not null")
    private String postal_code;

    @Column (columnDefinition = "varchar(20) not null")
    private String payment_method;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;
}
