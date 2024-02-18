package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(unique = true, columnDefinition = "varchar(50) not null")
    private String user_name;

    @Column(unique = true, columnDefinition = "varchar(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String password;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String fullname;

    @Column(unique = true, columnDefinition = "varchar(12)")
    private String phone;

    @Column
    private LocalDate dob;

    @Column
    private Boolean gender;

    @Column
    private boolean status;

    @Column (columnDefinition = "varchar(10)", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private Collection<Booking> bookings;

    @OneToMany(mappedBy = "user")
    private Collection<Timeshare> timeshares;

    @OneToMany(mappedBy = "user")
    private Collection<Transaction_history> transactionHistories;

    @OneToMany(mappedBy = "user")
    private Collection<Request> requests;

//    @OneToMany(mappedBy = "user")
//    private Collection<Exchange_information> exchangeInformations;

}
