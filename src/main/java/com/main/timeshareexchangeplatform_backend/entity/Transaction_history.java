package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "varchar(50)", name = "transaction_code")
    private String transactionCode;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime transaction_time;

    @Column(nullable = false)
    private long transaction_fee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service")
    private Service_pack servicePack;

}
