package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.*;

import java.util.List;
import java.util.UUID;

public interface TimeShareService {
   public List<TimeshareDTO> showListTimeShare();

   ResponseTimeshare createTimeshare(TimeshareDTO timeshareRespone);

   ResponseTimeshare getTimeshareByUserId(UUID userId);
   TimeshareRespone getTimeshareDetails(UUID timeshareId);

   public List<TimeshareDTO> getAllTimeshareUser(UUID userID);

   TimeshareDTO getImageById(UUID timeshare_id);

   String deactiveTimeshare(UUID timeshareId);

   String updateTimeshare(TimeshareDTO timeshareDTO);

//   List<Timeshare> getTimesharesCreatedWithinLast30Days();

}
