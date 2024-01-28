package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.DTO.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping({"/api"})
@RestController
public class TimeShareController {
    @Autowired TimeShareService timeShareService;
    @GetMapping({"/showall"})
    public ResponseEntity<?> showAllTimeShare() {
        List<TimeshareDTO> t = timeShareService.showListTimeShare();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }
}
