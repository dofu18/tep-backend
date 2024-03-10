package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;
import com.main.timeshareexchangeplatform_backend.service.IServicePackService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/sevicePack"})
public class SevicePackController {
    @Autowired
    IServicePackService iServicePackService;
    @GetMapping({"/viewAll"})

    public List<ServicePackDTO> findAll() {

        return iServicePackService.showAll();
    }

    @PutMapping("/edit")
    public String updateServicePack(@RequestBody ServicePackDTO servicePackDTO) {

        return iServicePackService.updateServicePack(servicePackDTO);
    }
}
