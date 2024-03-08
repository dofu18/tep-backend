package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;

import java.util.List;

public interface IServicePackService {

    public List<ServicePackDTO> showAll();

    String updateServicePack(ServicePackDTO servicePackDTO);
}
