package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exchange_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange_information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exchange_id;

    @Column
    private Boolean exchange_status;

}
