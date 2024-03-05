package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.ModelTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;

import java.util.List;
import java.util.UUID;

public interface TimeShareService {
   public List<TimeshareDTO> showListTimeShare();

   Boolean createTimeshare(TimeshareDTO timeshareRespone);

   TimeshareRespone getTimeshareByUserId(UUID userId);
   TimeshareRespone getTimeshareDetails(UUID timeshareId);

   public List<TimeshareDTO> getAllTimeshareUser(UUID userID);


}
