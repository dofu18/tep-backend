package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.RoomtypeConverter;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeModel;
import com.main.timeshareexchangeplatform_backend.entity.Roomtype;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.repository.RoomtypeRepository;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.service.IRoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomtypeImp implements IRoomtypeService {
    @Autowired
    private RoomtypeConverter roomtypeConverter;
    @Autowired
    private RoomtypeRepository roomtypeRepository;
    @Autowired
    TimeshareRepository timeshareRepository;
    @Override
    public boolean createRoomtype(RoomtypeModel roomtypeDTO) {
        Roomtype roomtype= new Roomtype();

        roomtype.setRoom_view(roomtypeDTO.getRoom_view());
        roomtype.setBath(roomtypeDTO.getBath());
        roomtype.setBed(roomtypeDTO.getBed());
        roomtype.setName(roomtypeDTO.getName());
        roomtype.setFeatures(roomtypeDTO.getFeatures());
        roomtype.setKitchen(roomtypeDTO.getKitchen());
        roomtype.setEntertainment(roomtypeDTO.getEntertaiment());
        roomtype.setPolicies(roomtypeDTO.getPolicies());
        roomtype.setSleeps(roomtypeDTO.getSleeps());
        roomtype.setTimeshare(timeshareRepository.getReferenceById(roomtypeDTO.getTimeshareId()));
        roomtypeRepository.save(roomtype);
        if(roomtypeRepository.findById(roomtype.getRoomtype_id()) !=null)
            return true;
        else return false;
    }

    @Override
    public RoomtypeDTO getRoomtypeByTimeshareId(UUID timeshare_id) {
        RoomtypeDTO roomtypeDTO= roomtypeConverter.toDTO(roomtypeRepository.getRoomtypeByTimeshareId(timeshare_id));

        return roomtypeDTO ;
    }
}

