package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModelResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.repository.RequestRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.service.IRequest;
import com.main.timeshareexchangeplatform_backend.vnpay.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

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

    @PutMapping("/response/{status}/{request_id}")
    public ResponseEntity<String> responseTimeshareExchange(@PathVariable int status,@PathVariable UUID request_id) {
        String result = requestService.reponseTimeshareExchange(status,request_id);
        if (result.equals("Exchange timeshare successfully")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("You have rejected to exchange timeshare")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("No course found")) {
            return ResponseEntity.badRequest().body(result);
        } else if (result.equals("This exchange is waiting to response")) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "/getRequestByReceiver")
    public List<RequestModelResponse> getAllRequestByReceiverId(@RequestParam UUID response_by) {
        return requestService.getAllRequestByResponseId(response_by);

    }

    @GetMapping(value = "/getRequestByRequestUser")
    public List<RequestModelResponse> getAllRequestByRequestUser(@RequestParam UUID resquest_by) {
        return requestService.getAllRequestByRequestUser(resquest_by);

    }
}
