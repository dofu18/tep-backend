package com.main.timeshareexchangeplatform_backend.dto;

import com.main.timeshareexchangeplatform_backend.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private UUID image_id;
    private String image_url;
    private Destination des;

}
