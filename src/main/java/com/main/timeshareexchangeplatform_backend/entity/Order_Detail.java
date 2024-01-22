package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "order_Detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_detail_id;

    @Column(columnDefinition = "DATE")
    private LocalDate create_date;

    @Column(columnDefinition = "DATE")
    private LocalDate success_date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "timeshare_id")
    private Timeshare timeshare;
}
