package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate create_date;

    @Column(columnDefinition = "nvarchar(max) not null")
    private String message;

    @Column
    private Boolean status;

//    @Column(columnDefinition = "nvarchar(max) not null")
//    private String request_to;



    @ManyToOne
    @JoinColumn(name = "resquest_by")
    private User user;

    @ManyToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;

    @OneToOne(mappedBy = "request")
    private Request_history requestHistory;

    @OneToOne
    @JoinColumn(name = "response_by")
    private Timeshare responseby;
}
