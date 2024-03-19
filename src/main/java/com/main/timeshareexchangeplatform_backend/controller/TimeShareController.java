package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.*;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequestMapping({"api/timeshare"})
@RestController
public class TimeShareController {
    @Autowired
    ITimeshareService timeShareService;
    @Autowired
    TimeShareService timeShareService1;
    @GetMapping({"/showall"})
    public ResponseEntity<?> showAllTimeShare() {
        List<ResponseTimeshare> t = timeShareService.showListTimeShare();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }

    @GetMapping({"/showAllTimeshareInactive"})
    public ResponseEntity<?> showAllTimeShareInactive() {
        List<ResponseTimeshare> t = timeShareService.showListTimeShareInactive();
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }

    @PostMapping({"homePage/{owner}"})
    public ResponseEntity<?> showAllTimeshareTrueExceptOwner(@PathVariable("owner") UUID id) {
        List<ResponseTimeshare> t = timeShareService.getAllTimeshareTrueExceptOwner(id);
        if (t != null) {
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timeshares found.");
        }

    }

    @GetMapping("/viewAll")
    public List<ResponseTimeshare> findAll() {

        return timeShareService.findAll();
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

    @GetMapping(value = "/getTimeshareByCity/{city}")
    public List<ResponseTimeshare> getAllTimeshareByCity(@PathVariable String city) {
        return timeShareService.findTimeshareByCity(city);

    }

    @PostMapping({"/createTimeshare"})
    public ResponseTimeshare createTimeshare(@RequestBody TimeshareDTO timeshareDTO){
        timeshareDTO.setCreate_date(LocalDate.now());
        return timeShareService1.createTimeshare(timeshareDTO) ;
    }

    @PutMapping("/deactive/{id}")
    public ResponseEntity<String> deactiveTimeshare(@PathVariable("id") UUID id) {
        String result = timeShareService1.deactiveTimeshare(id);
        if (result.equals("Successfully deactive timeshare")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("Timeshare was deactive already")) {
            return ResponseEntity.badRequest().body(result);
        } else if (result.equals("No timeshare found")) {
            return ResponseEntity.badRequest().body(result);
        }
        return null;
    }

    @PutMapping("/edit")
    public String updateRoomtype(@RequestBody TimeshareDTO timeshareDTO) {

        return timeShareService1.updateTimeshare(timeshareDTO);
    }

    @GetMapping("/last30days")
    public List<TimeshareDTO> getTimesharesCreatedWithinLast30Days() {
        return timeShareService.getTimesharesCreatedWithinLast30Days();
    }

}
