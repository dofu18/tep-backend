package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.repository.RequestRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.service.IRequest;
import com.main.timeshareexchangeplatform_backend.vnpay.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/request")
public class RequestController {
    @Autowired
    IRequest requestService;
    @Autowired
    RequestRepository requestRepository;

    @PostMapping("/create-request")
    public RequestModel createRequest(@RequestBody RequestModel requestModel) throws UnsupportedEncodingException {
        return requestService.createRequest(requestModel);
    }
}
