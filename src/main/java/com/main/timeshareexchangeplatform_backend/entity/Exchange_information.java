package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "exchange_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange_information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exchange_id;

    @Column
    private Boolean exchange_status;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String description;

    @Column(columnDefinition = "DATE")
    private LocalDate date_start;

    @Column(columnDefinition = "DATE")
    private LocalDate date_end;

    @OneToOne
    @JoinColumn (name = "timeshare_id")
    private Timeshare timeshare;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
