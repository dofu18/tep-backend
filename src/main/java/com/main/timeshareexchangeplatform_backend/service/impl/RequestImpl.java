package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.RequestConverter;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Request;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.repository.RequestRepository;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.IRequest;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class RequestImpl implements IRequest {
    @Autowired
    RequestConverter requestConverter;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    IRequest requestService;
    @Autowired
    TimeshareConverter timeshareConverter;
    @Autowired
    ITimeshareService timeshareService;
    @Autowired
    TimeshareRepository timeshareRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    UserRepository userRepository;
    @Override
    public RequestModel createRequest(RequestModel requestModel) {
//        UUID timeshareId = requestModel.getTimeshare_id();
        requestModel.setResponse_by(UUID.fromString("9d870e8c-0b4f-4e78-a9bf-16a45e7a42e1"));
        Request request = requestConverter.toEntity(requestModel);
//        Request existingRequest = requestRepository.findByRequestCode(requestModel.getRequestCode());
            request.setRequestCode(generateUniqueRequestCode());
            request.setMessage(request.getMessage());
            request.setStatus(false);
            request.setCreate_date(LocalDate.now());
            request.setTimeshare(timeshareService.getReferenceById(requestModel.getTimeshare_id()));
            request.setResponseby(request.getTimeshare().getPostBy());
            request = requestRepository.save(request);
//            Request timeshareRequest = new Request();
//            Timeshare t = timeshareRepository.findById(timeshareId).orElse(null);
//            if (t != null) {
//                timeshareRequest.setTimeshare(t);
//                timeshareRequest.setResponseby(t.getPostBy());
//            }

            RequestModel result = requestConverter.toDTO(request);
            return result;

    }
    private String generateUniqueRequestCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        // Concatenate the formatted date and time
        return "RES" + formattedDateTime;
    }

}
