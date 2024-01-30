package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.DTO.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;

import java.util.List;

public interface ITimeshareService {
   public List<TimeshareDTO> showListTimeShare();
   TimeshareRespone getTimeshareDetails(int timeshareId);

   Timeshare getReferenceById(Integer id);
}
