package com.main.timeshareexchangeplatform_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID image_id;

    @Column(columnDefinition = "varchar(max) not null")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
