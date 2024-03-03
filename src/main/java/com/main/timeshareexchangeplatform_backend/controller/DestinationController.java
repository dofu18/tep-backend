package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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


    
}
