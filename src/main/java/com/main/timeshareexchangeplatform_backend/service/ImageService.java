package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.ImageDTO;

import java.util.UUID;

public interface ImageService {
    public ImageDTO findImageByDestinationId(UUID destination_id);
}
