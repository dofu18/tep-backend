package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.RequestConverter;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.converter.UserConverter;
import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModelResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            request.setStatus(0);
            request.setCreate_date(LocalDate.now());
            request.setTimeshare_response(timeshareService.getReferenceById(requestModel.getTimeshare_response_id()));
            request.setResponseby(request.getTimeshare_response().getPostBy());
            request.setTimeshare_request(timeshareService.getReferenceById(requestModel.getTimeshare_request_id()));
            request.setResquestby(request.getTimeshare_request().getPostBy());
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

    @Override
    public String reponseTimeshareExchange(int status, UUID request_id) {
        Request request = requestRepository.getReferenceById(request_id);
//        UUID temporary_owner_1 = request.getResquestby().getUser_id();
//        UUID temporary_owner_2 = request.getResponseby().getUser_id();
        UUID timeshare_id_1 = request.getTimeshare_request().getTimeshare_id();
        UUID timeshare_id_2 = request.getTimeshare_response().getTimeshare_id();
        Timeshare timeshare_1 = timeshareRepository.findById(timeshare_id_1).orElse(null);
        Timeshare timeshare_2 = timeshareRepository.findById(timeshare_id_2).orElse(null);
        if (request != null) {
            request.setStatus(status);
            requestRepository.save(request);
            if (request.getStatus() == 1) {
                timeshare_1.setTemporary_owner(request.getResponseby().getUser_id());
                timeshare_2.setTemporary_owner(request.getResquestby().getUser_id());
                timeshareRepository.save(timeshare_1);
                timeshareRepository.save(timeshare_2);
                return "Exchange timeshare successfully";
            } else if (request.getStatus() == 2){
                return "You have rejected to exchange timeshare";
            } else {
                return "This exchange is waiting to response";
            }
        }

        return "No request found";
    }

    @Override
    public List<RequestModelResponse> getAllRequestByResponseId(UUID response_by) {
        List<Request> requestEntity = requestRepository.getAllRequestByResponseId(response_by);
        List<RequestModelResponse> requestRespones = requestEntity.stream().map(requestConverter::toDTOResponse).collect(Collectors.toList());

        return requestRespones;
    }

    @Override
    public List<RequestModelResponse> getAllRequestByRequestUser(UUID resquest_by) {
        List<Request> requestEntity = requestRepository.getAllRequestByRequestUser(resquest_by);
        List<RequestModelResponse> requestRespones = requestEntity.stream().map(requestConverter::toDTOResponse).collect(Collectors.toList());

        return requestRespones;
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
