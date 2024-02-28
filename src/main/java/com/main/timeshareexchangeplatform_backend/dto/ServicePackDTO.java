package com.main.timeshareexchangeplatform_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePackDTO {

    private UUID service_id;
    private int ad_duration;
    private boolean allow_post;
    private int flag;
    private String name;
    private boolean priority;
    private String service_code;
    private long service_price;

}
