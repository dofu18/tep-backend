package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.ImageConverter;
import com.main.timeshareexchangeplatform_backend.dto.ImageDTO;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.repository.ImageRepository;
import com.main.timeshareexchangeplatform_backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageImp implements ImageService {
    @Autowired
    ImageConverter imageConverter;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageDTO findImageByDestinationId(UUID destination_id) {
        ImageDTO imageDTO = imageConverter.toDTO(imageRepository.findImageByDestinationId(destination_id));

        return imageDTO;

    }
}
