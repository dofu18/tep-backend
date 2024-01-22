package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestHistory_id;

    @Column (columnDefinition = "DATETIME")
    private LocalDateTime update_datetime;

    @Column(columnDefinition = "varchar(max) not null")
    private String detail;

    @Column
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;
}
