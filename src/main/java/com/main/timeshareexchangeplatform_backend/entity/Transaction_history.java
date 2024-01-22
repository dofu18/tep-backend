package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    @Column(unique = true, columnDefinition = "varchar(50)")
    private String transaction_type;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime transaction_time;

    @Column
    private float transaction_fee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
