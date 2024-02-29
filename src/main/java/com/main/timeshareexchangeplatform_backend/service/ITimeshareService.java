package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;

import java.util.List;
import java.util.UUID;

public interface ITimeshareService {
   public List<TimeshareDTO> showListTimeShare();
   TimeshareRespone getTimeshareDetails(UUID timeshareId);
   TimeshareRespone getTimeshareByUserId(UUID userId);

   TimeshareDTO addTimeshare(TimeshareDTO timeshareDTO);

    List<TimeshareDTO> getAllTimeshareUser(UUID userId);

    Timeshare getReferenceById(UUID id);

   List<ResponseTimeshare> findTimeshareByName(String name);

   List<ResponseTimeshare> getAllTimeshareByUserId(UUID userid);

}
