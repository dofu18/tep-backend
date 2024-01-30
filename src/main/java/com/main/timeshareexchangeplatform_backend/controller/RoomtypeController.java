package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping({"/api"})
@RestController
public class RoomtypeController {
    @Autowired
    ITimeshareService timeShareService;
    @GetMapping({"/home"})
    public ResponseEntity<?> showAllTimeShare() {
        List<TimeshareDTO> t = timeShareService.showListTimeShare();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }
}
