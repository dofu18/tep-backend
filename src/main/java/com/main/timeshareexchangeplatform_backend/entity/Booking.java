package com.main.timeshareexchangeplatform_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(unique = true, columnDefinition = "nvarchar(50)", name = "booking_code", nullable = false)
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

    @Column (columnDefinition = "nvarchar(15) not null")
    private String telephone;

    @Column (columnDefinition = "nvarchar(50)")
    private String fullname;

    @Column (columnDefinition = "nvarchar(50) not null")
    private String country;

    @Column (columnDefinition = "nvarchar(50) not null")
    private String street;

    @Column (columnDefinition = "nvarchar(50) not null")
    private String city;

    @Column (columnDefinition = "nvarchar(50) not null")
    private String state;

    @Column (columnDefinition = "nvarchar(20) not null")
    private String postal_code;

    @Column (columnDefinition = "nvarchar(20) not null")
    private String payment_method;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;
}
