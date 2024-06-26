package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModelResponse;
import com.main.timeshareexchangeplatform_backend.entity.Request;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestConverter {

    @Autowired
    UserConverter userConverter;
    @Autowired
    TimeshareConverter timeshareConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeshareRepository timeshareRepository;

    public Request toEntityResponse(RequestModelResponse model) {

        Request entity = new Request();

        entity. setRequest_id(model.getRequest_id());
        entity.setCreate_date(model.getCreate_date());
        entity.setStatus(model.getStatus());
        entity.setResponseby(userConverter.toEntity(model.getResponse_by()));
        entity.setResquestby(userConverter.toEntity(model.getRequest_by()));
        entity.setTimeshare_request(timeshareConverter.toResEntity(model.getTimeshare_request_id()));
        entity.setTimeshare_response(timeshareConverter.toResEntity(model.getTimeshare_response_id()));
        entity.setMessage(model.getMessage());
        entity.setRequestCode(model.getRequest_code());

        return entity;
    }

    public RequestModelResponse toDTOResponse(Request entity) {
        RequestModelResponse dto = new RequestModelResponse();

        dto.setRequest_id(entity.getRequest_id());
        dto.setCreate_date(entity.getCreate_date());
        dto.setStatus(entity.getStatus());
        dto.setRequest_by(userConverter.toDTO(entity.getResquestby()));
        dto.setTimeshare_request_id(timeshareConverter.toRespone(entity.getTimeshare_request()));
        dto.setTimeshare_response_id(timeshareConverter.toRespone((entity.getTimeshare_response())));
        dto.setResponse_by(userConverter.toDTO(entity.getResponseby()));
        dto.setMessage(entity.getMessage());
        dto.setRequest_code(entity.getRequestCode());
        dto.setRequest_code(entity.getRequestCode());
        return dto;
    }

    public Request toEntity(RequestModel model) {

        Request entity = new Request();

        entity. setRequest_id(model.getRequest_id());
        entity.setCreate_date(model.getCreate_date());
        entity.setStatus(model.getStatus());
        entity.setResquestby(userRepository.getReferenceById(model.getRequest_by()));
        entity.setTimeshare_request(timeshareRepository.getReferenceById(model.getTimeshare_request_id()));
        entity.setTimeshare_response(timeshareRepository.getReferenceById(model.getTimeshare_response_id()));
        entity.setResponseby(userRepository.getReferenceById(model.getResponse_by()));
        entity.setMessage(model.getMessage());
        entity.setRequestCode(model.getRequestCode());

        return entity;
    }

    public RequestModel toDTO(Request entity) {
        RequestModel dto = new RequestModel();

        dto.setRequest_id(entity.getRequest_id());
        dto.setCreate_date(entity.getCreate_date());
        dto.setStatus(entity.getStatus());
        dto.setResponse_by(entity.getResponseby().getUser_id());
        dto.setRequest_by(entity.getResquestby().getUser_id());
        dto.setTimeshare_request_id(entity.getTimeshare_request().getTimeshare_id());
        dto.setTimeshare_response_id(entity.getTimeshare_response().getTimeshare_id());
        dto.setMessage(entity.getMessage());
        dto.setRequestCode(entity.getRequestCode());

        return dto;
    }
}
