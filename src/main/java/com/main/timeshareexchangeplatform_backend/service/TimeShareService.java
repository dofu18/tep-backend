package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.DTO.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;

import java.util.List;

public interface TimeShareService {
   public List<TimeshareDTO> showListTimeShare();
}
