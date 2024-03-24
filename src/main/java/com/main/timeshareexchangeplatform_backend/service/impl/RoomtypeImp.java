package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.RoomtypeConverter;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeModel;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
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
    public RoomtypeDTO createRoomtype(RoomtypeModel roomtypemodel) {
        Roomtype roomtype= new Roomtype();


        roomtype.setRoom_view(roomtypemodel.getRoom_view());
        roomtype.setBath(roomtypemodel.getBath());
        roomtype.setBed(roomtypemodel.getBed());
        roomtype.setName(roomtypemodel.getName());
        roomtype.setFeatures(roomtypemodel.getFeatures());
        roomtype.setKitchen(roomtypemodel.getKitchen());
        roomtype.setEntertainment(roomtypemodel.getEntertaiment());
        roomtype.setPolicies(roomtypemodel.getPolicies());
        roomtype.setSleeps(roomtypemodel.getSleeps());
        roomtype.setTimeshare(timeshareRepository.getReferenceById(roomtypemodel.getTimeshareId()));
        roomtypeRepository.save(roomtype);
        RoomtypeDTO result= roomtypeConverter.toDTO(roomtype);
        return result;
    }

    @Override
    public RoomtypeDTO getRoomtypeByTimeshareId(UUID timeshare_id) {
        RoomtypeDTO roomtypeDTO= roomtypeConverter.toDTO(roomtypeRepository.getRoomtypeByTimeshareId(timeshare_id));

        return roomtypeDTO ;
    }

    @Override
    public String updateRoomtype(RoomtypeModel roomtypeModel) {
        Roomtype entity = roomtypeRepository.getReferenceById(roomtypeModel.getRoomtypeId());
        if (entity.getRoomtype_id() == roomtypeModel.getRoomtypeId()) {

            entity.setBath(roomtypeModel.getBath());
            entity.setBed(roomtypeModel.getBed());
            entity.setEntertainment(roomtypeModel.getEntertaiment());
            entity.setFeatures(roomtypeModel.getFeatures());
            entity.setKitchen(roomtypeModel.getKitchen());
            entity.setName(roomtypeModel.getName());
            entity.setPolicies(roomtypeModel.getPolicies());
            entity.setRoom_view(roomtypeModel.getRoom_view());
            entity.setSleeps(roomtypeModel.getSleeps());
            entity.setTimeshare(timeshareRepository.getReferenceById(roomtypeModel.getTimeshareId()));

            roomtypeRepository.save(entity);
            return "Update Successfully";
        }
        return "Fail to update";
    }
}

