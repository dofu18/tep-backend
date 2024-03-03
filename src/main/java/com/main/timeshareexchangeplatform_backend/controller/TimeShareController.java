package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping({"api/timeshare"})
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

    public ResponseEntity<?> getTimeshareDetails(@PathVariable UUID timeshareId) {
        ResponseTimeshare timeshareRespone;
        timeshareRespone = timeShareService.findTimeshareByTimeshareId(timeshareId);

        if (timeshareRespone != null) {
            return ResponseEntity.status(HttpStatus.OK).body(timeshareRespone);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found for timeshareId: " + timeshareId);
        }
    }

    @GetMapping("/details/user/{userId}")

    public ResponseEntity<?> getTimeshareByUserId(@PathVariable UUID userId) {
        TimeshareRespone timeshareRespone;
        timeshareRespone = timeShareService.getTimeshareByUserId(userId);

        if (timeshareRespone != null) {
            return ResponseEntity.status(HttpStatus.OK).body(timeshareRespone);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found for timeshareId: " + userId);
        }
    }
    @GetMapping({"/details/user/showall/{userId}"})
    public ResponseEntity<?> showAllTimeShareUser(@PathVariable UUID userId) {
        List<TimeshareDTO> t = timeShareService.getAllTimeshareUser(userId);
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }

    @GetMapping(value = "/find-by-title")
    public List<ResponseTimeshare> findTimeshareByName(@RequestParam("name") String name) {
        return timeShareService.findTimeshareByName(name);
    }

    //Get all timeshare by user id
    @GetMapping(value = "/getTimeshareByOwner")
    public List<ResponseTimeshare> getAllTimeshareByUserId(@RequestParam UUID owner) {
        return timeShareService.getAllTimeshareByUserId(owner);

    }
}
