package com.main.timeshareexchangeplatform_backend.converter;


import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TimeshareConverter {
    @Autowired
    UserConverter userConverter;
    @Autowired
    DestinationConverter destinationConverter;




    public static TimeshareDTO toDTO(Timeshare timeshare) {
        if (timeshare == null) {
            return null;
        }

        TimeshareDTO dto = new TimeshareDTO();
        dto.setTimeshare_id(timeshare.getTimeshare_id());
        dto.setDate_start(timeshare.getDate_start());
        dto.setDate_end(timeshare.getDate_end());
        dto.setNights(timeshare.getNights());
        dto.setPrice(timeshare.getPrice());
        dto.setStatus(timeshare.isStatus());
        dto.setName(timeshare.getName());
//        dto.setAddress(timeshare.getAddress());
        dto.setPost_by(UUID.fromString(String.valueOf(timeshare.getPostBy().getUser_id())));  // Assuming there is a User entity in Timeshare
        dto.setDestination_id(timeshare.getDestination().getDestination_id());  // Assuming there is a Destination entity in Timeshare
        dto.setDescription(timeshare.getDescription());
        dto.setImage_url(timeshare.getImage_url());
        return dto;
    }

    public static Timeshare toEntity(TimeshareDTO dto, User postedBy, Destination destination) {
        if (dto == null) {
            return null;
        }

        Timeshare timeshare = new Timeshare();
        timeshare.setTimeshare_id(dto.getTimeshare_id());
        timeshare.setDate_start(dto.getDate_start());
        timeshare.setDate_end(dto.getDate_end());
        timeshare.setNights(dto.getNights());
        timeshare.setPrice(dto.getPrice());
        timeshare.setStatus(dto.isStatus());
        timeshare.setName(timeshare.getName());
//        timeshare.setAddress(dto.getAddress());
        timeshare.setPostBy(postedBy);
        timeshare.setDestination(destination);
        timeshare.setDescription(dto.getDescription());
        timeshare.setImage_url(dto.getImage_url());
        return timeshare;
    }

    public ResponseTimeshare toRespone(Timeshare timeshareEntity) {
        ResponseTimeshare dto = new ResponseTimeshare();
        dto.setTimeshareId(timeshareEntity.getTimeshare_id());
        dto.setTimeshareName(timeshareEntity.getName());
        dto.setDescription(timeshareEntity.getDescription());
        dto.setDateStart(timeshareEntity.getDate_start());
        dto.setDateEnd(timeshareEntity.getDate_end());
        dto.setExchange(timeshareEntity.getExchange());
        dto.setPrice(timeshareEntity.getPrice());
        dto.setStatus(timeshareEntity.isStatus());
        dto.setNights(timeshareEntity.getNights());
        dto.setPostBy(userConverter.toDTO(timeshareEntity.getPostBy()));
        dto.setDestinationModel(destinationConverter.toDTO(timeshareEntity.getDestination()));

        return dto;
    }

    public Timeshare toResEntity(ResponseTimeshare model) {
        Timeshare entity = new Timeshare();

        entity.setTimeshare_id(model.getTimeshareId());
        entity.setName(model.getTimeshareName());
        entity.setDescription(model.getDescription());
        entity.setDate_start(model.getDateStart());
        entity.setDate_end(model.getDateEnd());
        entity.setExchange(model.isExchange());
        entity.setPrice(model.getPrice());
        entity.setStatus(model.isStatus());
        entity.setNights(model.getNights());
        entity.setPostBy(userConverter.toEntity(model.getPostBy()));
        entity.setDestination(destinationConverter.toEntity(model.getDestinationModel()));

        return entity;
    }



    public List<TimeshareDTO> convertToAccountPlaylistDTOList(List<Timeshare> StudentList) {
        List<TimeshareDTO> StudentDTOList = new ArrayList<>();
        for (Timeshare accountPlaylist : StudentList) {
            StudentDTOList.add(toDTO(accountPlaylist));
        }
        return StudentDTOList;
    }
}
