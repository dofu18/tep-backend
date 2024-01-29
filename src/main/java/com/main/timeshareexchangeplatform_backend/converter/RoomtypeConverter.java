package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.entity.Roomtype;

import java.util.ArrayList;
import java.util.List;
public class RoomtypeConverter {
    public static RoomtypeDTO toDTO(Roomtype roomType) {
        if (roomType == null) {
            return null;
        }

        RoomtypeDTO dto = new RoomtypeDTO();
        dto.setRoomtypeId(roomType.getRoomtype_id());
        dto.setBath(roomType.getBath());
        dto.setBed(roomType.getBed());
        dto.setSleeps(roomType.getSleeps());
        dto.setEntertaiment(roomType.getEntertainment());
        dto.setFeature(roomType.getFeatures());
        dto.setKitchen(roomType.getKitchen());
        dto.setName(roomType.getName());
        dto.setPolicies(roomType.getPolicies());
        dto.setRoomview(roomType.getRoom_view());

        return dto;
    }

    public static Roomtype toEntity(RoomtypeDTO dto) {
        if (dto == null) {
            return null;
        }

        Roomtype roomType = new Roomtype();
        roomType.setRoomtype_id(dto.getRoomtypeId());
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
    public List<RoomtypeDTO> convertToAccountPlaylistDTOList(List<Roomtype> StudentList) {
        List<RoomtypeDTO> RoomtypeDTOList = new ArrayList<>();
        for (Roomtype accountPlaylist : StudentList) {
            RoomtypeDTOList.add(toDTO(accountPlaylist));
        }
        return RoomtypeDTOList;
    }

}
