package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.DTO.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.respone.TimeshareRespone;

import java.util.List;
import java.util.Map;

public interface TimeShareService {
   public List<TimeshareDTO> showListTimeShare();
   TimeshareRespone getTimeshareDetails(int timeshareId);
}
