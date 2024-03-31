package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;

import java.util.List;

public interface IDestinationService {
    List<DestinationModel> findAll();

    DestinationModel creatDestination(DestinationModel destinationModel);

    String updateDestination(DestinationModel destinationModel);
}
