package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.DestinationDTO;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;
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
    public DestinationModel createDestination(@RequestBody DestinationModel destinationDTO){

        return DestinationService.creatDestination(destinationDTO);
    }

    @PutMapping("/edit")
    public String updateDedstination(@RequestBody DestinationModel destinationModel) {

        return DestinationService.updateDestination(destinationModel);
    }
}
