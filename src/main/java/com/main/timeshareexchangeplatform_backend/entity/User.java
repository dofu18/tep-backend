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

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String user_name;

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String email;

    @Column
    private String password;

    @Column(columnDefinition = "nvarchar(50)")
    private String fullname;

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String phone;

    @Column
    private LocalDate dob;

    @Column
    private Boolean gender;

    @Column
    private boolean status;

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
