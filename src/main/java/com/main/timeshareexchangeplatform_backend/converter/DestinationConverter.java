package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationConverter {

    public Destination toEntity(DestinationModel model) {

        Destination entity = new Destination();
        entity.setDestination_id(model.getDestinationId());
        entity.setAddress(model.getAddress());
        entity.setBranch(model.getBranch());
        entity.setCity(model.getCity());
        entity.setCountry(model.getCountry());
        entity.setDescription(model.getDescription());
        entity.setName(model.getDesName());

        return entity;
    }

    public DestinationModel toDTO(Destination entity) {
        DestinationModel dto = new DestinationModel();
        dto.setDestinationId(entity.getDestination_id());
        dto.setAddress(entity.getAddress());
        dto.setBranch(entity.getBranch());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setDescription(entity.getDescription());
        dto.setDesName(entity.getName());


        return dto;
    }
}
