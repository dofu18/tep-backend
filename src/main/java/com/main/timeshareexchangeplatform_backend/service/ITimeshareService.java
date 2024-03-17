package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;

import java.util.List;
import java.util.UUID;

public interface ITimeshareService {
   List<ResponseTimeshare> showListTimeShare();
   Timeshare getTimeshareDetails(UUID timeshareId);
   TimeshareRespone getTimeshareByUserId(UUID userId);

    List<TimeshareDTO> getTimesharesCreatedWithinLast30Days();


    List<TimeshareDTO> getAllTimeshareUser(UUID userId);

    Timeshare getReferenceById(UUID id);

   List<ResponseTimeshare> findTimeshareByName(String name);

   ResponseTimeshare findTimeshareByTimeshareId(UUID timeshare_id);

   List<ResponseTimeshare> getAllTimeshareByUserId(UUID userid);

    List<ResponseTimeshare> findTimeshareByCity(String city);

    List<ResponseTimeshare> getAllTimeshareTrueExceptOwner(UUID owner);

    List<ResponseTimeshare> findAll();

    List<ResponseTimeshare> showListTimeShareInactive();
}
