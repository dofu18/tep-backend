package com.main.timeshareexchangeplatform_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID user_id;

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String user_name;

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String email;

    @Column
    @JsonIgnore
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

    @Column (columnDefinition = "varchar(10)")
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
