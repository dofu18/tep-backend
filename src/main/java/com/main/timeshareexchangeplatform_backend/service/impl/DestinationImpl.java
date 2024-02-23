package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.DestinationConverter;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.repository.DestinationRepository;
import com.main.timeshareexchangeplatform_backend.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationImpl implements IDestinationService {
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    DestinationConverter destinationConverter;

    @Override
    public List<DestinationModel> findAll() {
        List<DestinationModel> courseModels = destinationRepository.findAll().stream().map(destinationConverter::toDTO)
                .collect(Collectors.toList());

        return courseModels;

    }
}
