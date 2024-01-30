package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int booking_id;

    @Column(unique = true, columnDefinition = "varchar(50)", name = "booking_code")
    private String bookingCode;

    @Column
    private long total;

    @Column
    private boolean payment_status;

    @Column(columnDefinition = "DATE")
    private LocalDate create_date;

    @Column(columnDefinition = "DATE")
    private LocalDate success_date;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;
}
