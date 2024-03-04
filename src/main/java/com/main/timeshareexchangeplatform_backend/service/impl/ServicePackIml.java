package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.ServicePackConverter;
import com.main.timeshareexchangeplatform_backend.dto.ServicePackDTO;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import com.main.timeshareexchangeplatform_backend.repository.ServicePackRepository;
import com.main.timeshareexchangeplatform_backend.service.IServicePackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePackIml implements IServicePackService {
@Autowired
ServicePackConverter servicePackConverter;
@Autowired
ServicePackRepository servicePackRepository;
    @Override
    public List<ServicePackDTO> showAll() {
        List<Service_pack> servicePacks = servicePackRepository.showAllSevicePack();
        return servicePackConverter.convertToAccountPlaylistDTOList(servicePacks);
    }
}
