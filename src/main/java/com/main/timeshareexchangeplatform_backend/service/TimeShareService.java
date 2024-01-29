package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;

import java.util.List;

public interface TimeShareService {
   public List<TimeshareDTO> showListTimeShare();
   TimeshareRespone getTimeshareDetails(int timeshareId);
}
