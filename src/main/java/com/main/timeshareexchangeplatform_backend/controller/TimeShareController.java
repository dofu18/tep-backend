package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"/api/timeshare"})
@RestController
public class TimeShareController {
    @Autowired
    ITimeshareService timeShareService;
    @GetMapping({"/showall"})
    public ResponseEntity<?> showAllTimeShare() {
        List<TimeshareDTO> t = timeShareService.showListTimeShare();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }
    @GetMapping("/details/{timeshareId}")
    public ResponseEntity<?> getTimeshareDetails(@PathVariable int timeshareId) {
        TimeshareRespone timeshareRespone;
        timeshareRespone = timeShareService.getTimeshareDetails(timeshareId);

        if (timeshareRespone != null) {
            return ResponseEntity.status(HttpStatus.OK).body(timeshareRespone);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found for timeshareId: " + timeshareId);
        }
    }
}
