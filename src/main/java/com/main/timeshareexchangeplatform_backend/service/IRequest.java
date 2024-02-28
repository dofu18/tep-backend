package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModelResponse;
import com.main.timeshareexchangeplatform_backend.entity.Request;

import java.util.UUID;

public interface IRequest {

    RequestModel createRequest(RequestModel requestModel);

    String reponseTimeshareExchange(int status, UUID request_id);
}
