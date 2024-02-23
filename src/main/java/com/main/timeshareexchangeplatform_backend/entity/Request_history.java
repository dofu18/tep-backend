package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request_history {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID requestHistory_id;

    @Column (columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime update_datetime;

    @Column(columnDefinition = "varchar(max) not null")
    private String detail;

    @Column
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;
}
