package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_history {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transaction_id;

    @Column(columnDefinition = "nvarchar(50)", name = "transaction_code", unique = true)
    private String transactionCode;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime transaction_time;

    @Column(nullable = false)
    private long transaction_fee;

    @Column(columnDefinition = "DATETIME", name = "expired_date")
    private LocalDateTime expireDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service")
    private Service_pack servicePack;

}
