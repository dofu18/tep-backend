package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeModel;
import com.main.timeshareexchangeplatform_backend.entity.Roomtype;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class RoomtypeConverter {
    @Autowired
    TimeshareConverter timeshareConverter;
    @Autowired
    TimeshareRepository timeshareRepository;
    public  RoomtypeDTO toDTO(Roomtype roomType) {
        if (roomType == null) {
            return null;
        }

        RoomtypeDTO dto = new RoomtypeDTO();
        dto.setRoomtypeId(String.valueOf(roomType.getRoomtype_id()));
        dto.setBath(roomType.getBath());
        dto.setBed(roomType.getBed());
        dto.setSleeps(roomType.getSleeps());
        dto.setEntertaiment(roomType.getEntertainment());
        dto.setFeature(roomType.getFeatures());
        dto.setKitchen(roomType.getKitchen());
        dto.setName(roomType.getName());
        dto.setPolicies(roomType.getPolicies());
        dto.setRoomview(roomType.getRoom_view());
        dto.setTimeshareId(timeshareConverter.toDTO(roomType.getTimeshare()));
        return dto;
    }

    public  Roomtype toEntity(RoomtypeDTO dto) {
        if (dto == null) {
            return null;
        }

        Roomtype roomType = new Roomtype();
        roomType.setRoomtype_id(UUID.fromString(dto.getRoomtypeId()));
        roomType.setBath(dto.getBath());
        roomType.setBed(dto.getBed());
        roomType.setSleeps(dto.getSleeps());
        roomType.setEntertainment(dto.getEntertaiment());
        roomType.setFeatures(dto.getFeature());
        roomType.setKitchen(dto.getKitchen());
        roomType.setName(dto.getName());
        roomType.setPolicies(dto.getPolicies());
        roomType.setRoom_view(dto.getRoomview());

        return roomType;
    }
//    public RoomtypeModel toModel   (RoomtypeDTO roomtypedto) {
//        if (roomtypedto == null) {
//            return null;
//        }
//
//        RoomtypeModel dto = new RoomtypeModel();
//        dto.setRoomtypeId(UUID.fromString(roomtypedto.getRoomtypeId()));
//        dto.setBath(roomtypedto.getBath());
//        dto.setBed(roomtypedto.getBed());
//        dto.setSleeps(roomtypedto.getSleeps());
//        dto.setEntertaiment(roomtypedto.getEntertaiment());
//        dto.setFeatures(roomtypedto.getFeature());
//        dto.setKitchen(roomtypedto.getKitchen());
//        dto.setName(roomtypedto.getName());
//        dto.setPolicies(roomtypedto.getPolicies());
//        dto.setRoom_view(roomtypedto.getRoomview());
//        dto.setTimeshareId(roomtypedto.getTimeshareId());
//        return dto;
//    }

    public List<RoomtypeDTO> convertToAccountPlaylistDTOList(List<Roomtype> StudentList) {
        List<RoomtypeDTO> RoomtypeDTOList = new ArrayList<>();
        for (Roomtype accountPlaylist : StudentList) {
            RoomtypeDTOList.add(toDTO(accountPlaylist));
        }
        return RoomtypeDTOList;
    }

}
