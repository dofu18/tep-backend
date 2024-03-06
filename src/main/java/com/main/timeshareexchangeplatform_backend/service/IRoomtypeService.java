package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;

import java.util.UUID;

public interface IRoomtypeService {

    boolean createRoomtype(RoomtypeDTO roomtypeDTO);

    RoomtypeDTO getRoomtypeByTimeshareId(UUID roomtype_id);
}
