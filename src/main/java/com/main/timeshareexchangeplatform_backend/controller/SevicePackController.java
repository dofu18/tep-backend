package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;
import com.main.timeshareexchangeplatform_backend.service.IServicePackService;
import com.main.timeshareexchangeplatform_backend.service.ITransactionSevice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"api/sevicePack"})
public class SevicePackController {
    @Autowired
    IServicePackService iServicePackService;

    @Autowired
    ITransactionSevice transactionSevice;
    @GetMapping({"/viewAll"})

    public List<ServicePackDTO> findAll() {

        return iServicePackService.showAll();
    }

    @PutMapping("/edit")
    public String updateServicePack(@RequestBody ServicePackDTO servicePackDTO) {

        return iServicePackService.updateServicePack(servicePackDTO);
    }

    @GetMapping("/total-transaction-service")
    public long getTotalSum() {
        return transactionSevice.getTotalSum();
    }

    @GetMapping("/total-service-even-user/{userId}")
    public long getTotalSumByUserId(@PathVariable UUID userId) {
        return transactionSevice.getTotalSumByUserId(userId);
    }
}
