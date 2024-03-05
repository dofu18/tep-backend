package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;

import com.main.timeshareexchangeplatform_backend.repository.RoomtypeRepository;
import com.main.timeshareexchangeplatform_backend.service.IRoomtypeService;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import com.main.timeshareexchangeplatform_backend.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping({"/api/roomtype"})
@RestController
public class RoomtypeController {
    @Autowired
    ITimeshareService timeShareService;
    @Autowired
    RoomtypeRepository roomtypeRepository;
    @Autowired
    IRoomtypeService iRoomtypeService;
    @GetMapping({"/home"})
    public ResponseEntity<?> showAllTimeShare() {
        List<TimeshareDTO> t = timeShareService.showListTimeShare();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }

    @PostMapping({"/createRoomtype"})
    public boolean createRoomtype(@RequestBody RoomtypeDTO roomtypeDTO){
        return iRoomtypeService.createRoomtype(roomtypeDTO);
    }
}
