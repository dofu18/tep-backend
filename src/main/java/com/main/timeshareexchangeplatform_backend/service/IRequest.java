package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.RequestModel;
import com.main.timeshareexchangeplatform_backend.dto.RequestModelResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import org.apache.coyote.Request;

import java.util.List;
import java.util.UUID;

public interface IRequest {

    RequestModel createRequest(RequestModel requestModel);

    String reponseTimeshareExchange(int status, UUID request_id);

    List<RequestModelResponse> getAllRequestByResponseId(UUID response_by);

    List<RequestModelResponse> getAllRequestByRequestUser(UUID resquest_by);
}
