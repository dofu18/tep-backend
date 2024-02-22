package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;

import java.util.List;

public interface IDestinationService {
    List<DestinationModel> findAll();
}
