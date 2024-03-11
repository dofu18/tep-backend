package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.*;

import com.main.timeshareexchangeplatform_backend.repository.RoomtypeRepository;
import com.main.timeshareexchangeplatform_backend.service.IRoomtypeService;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import com.main.timeshareexchangeplatform_backend.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public boolean createRoomtype(@RequestBody RoomtypeModel roomtypeDTO){
        return iRoomtypeService.createRoomtype(roomtypeDTO);
    }

    @GetMapping({"/details/{timeshare_id}"})
    public ResponseEntity<?> getRoomtypeByTimeshareId(@PathVariable UUID timeshare_id) {
        RoomtypeDTO roomtypeDTO;
        roomtypeDTO = iRoomtypeService.getRoomtypeByTimeshareId(timeshare_id);

        if (roomtypeDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(roomtypeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found for timeshareId: " + timeshare_id);
        }
    }

    @PutMapping("/edit")
    public String updateRoomtype(@RequestBody RoomtypeModel roomtypeModel) {

        return iRoomtypeService.updateRoomtype(roomtypeModel);
    }
}
