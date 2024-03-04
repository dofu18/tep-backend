package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.DestinationDTO;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping({"/api/destination"})
@RestController
public class DestinationController {
    @Autowired
    IDestinationService DestinationService;
    @GetMapping("/viewAll")

    public List<DestinationModel> findAll() {

        return DestinationService.findAll();
    }

    @PostMapping("/createDestination")
    public boolean createDestination(@RequestBody DestinationModel destinationDTO){

        return DestinationService.creatDestination(destinationDTO);
    }
    
}
