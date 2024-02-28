package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "service_pack")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service_pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID service_id;

    @Column(columnDefinition = "nvarchar(10)")
    private String service_code;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    @Column
    private boolean allow_post;

    @Column
    private int ad_duration;

    @Column
    private int flag;

    @Column
    private boolean priority;

    @OneToMany(mappedBy = "servicePack")
    private Collection<Transaction_history> transactionHistories;
}
