package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeModel;

import java.util.UUID;

public interface IRoomtypeService {

    boolean createRoomtype(RoomtypeModel roomtypeDTO);

    RoomtypeDTO getRoomtypeByTimeshareId(UUID roomtype_id);
}
