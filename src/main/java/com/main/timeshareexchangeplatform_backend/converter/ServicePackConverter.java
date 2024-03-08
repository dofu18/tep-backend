package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServicePackConverter {

    public Service_pack toEntity(ServicePackDTO dto) {

        Service_pack entity = new Service_pack();

        entity.setService_id(dto.getService_id());
        entity.setAd_duration(dto.getAd_duration());
        entity.setAllow_post(dto.isAllow_post());
        entity.setFlag(dto.isFlag());
        entity.setName(dto.getName());
        entity.setPriority(dto.isPriority());
        entity.setService_code(dto.getService_code());
        entity.setService_price(dto.getService_price());

        return entity;
    }

    public ServicePackDTO toDTO(Service_pack entity) {

        ServicePackDTO dto = new ServicePackDTO();

        dto.setService_id(entity.getService_id());
        dto.setAd_duration(entity.getAd_duration());
        dto.setAllow_post(entity.isAllow_post());
        dto.setFlag(entity.isFlag());
        dto.setName(entity.getName());
        dto.setPriority(entity.isPriority());
        dto.setService_code(entity.getService_code());
        dto.setService_price(entity.getService_price());


        return dto;
    }
    public List<ServicePackDTO> convertToAccountPlaylistDTOList(List<Service_pack> servicePacksList) {
        List<ServicePackDTO> Service_packDTOList = new ArrayList<>();
        for (Service_pack accountPlaylist : servicePacksList) {
            Service_packDTOList.add(toDTO(accountPlaylist));
        }
        return Service_packDTOList;
    }
}
