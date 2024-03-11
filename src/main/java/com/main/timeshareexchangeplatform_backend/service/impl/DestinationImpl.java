package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.DestinationConverter;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import com.main.timeshareexchangeplatform_backend.repository.DestinationRepository;
import com.main.timeshareexchangeplatform_backend.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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

    @Override
    public boolean creatDestination(DestinationModel destinationModel) {
        Destination destination = new Destination();

        destination.setName(destinationModel.getDesName());
        destination.setCity(destinationModel.getCity());
        destination.setCountry(destinationModel.getCountry());
        destination.setBranch(destinationModel.getBranch());
        destination.setDescription(destinationModel.getDescription());
        destination.setAddress(destinationModel.getAddress());

        destinationRepository.save(destination);
        if (destinationRepository.getById(destination.getDestination_id()) != null)
        return true;
        else return false;
    }

    @Override
    public String updateDestination(DestinationModel destinationModel) {
        Destination entity = destinationRepository.getReferenceById(destinationModel.getDestinationId());
        if (entity.getDestination_id() == destinationModel.getDestinationId()) {
            entity.setAddress(destinationModel.getAddress());
            entity.setBranch(destinationModel.getBranch());
            entity.setCity(destinationModel.getCity());
            entity.setCountry(destinationModel.getCountry());
            entity.setDescription(destinationModel.getDescription());
            entity.setName(destinationModel.getDesName());

            destinationRepository.save(entity);
            return "Update Successfully";
        }
        return "Fail to update";
    }
}
