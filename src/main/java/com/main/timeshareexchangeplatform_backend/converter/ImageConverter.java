package com.main.timeshareexchangeplatform_backend.converter;
import com.main.timeshareexchangeplatform_backend.dto.ImageDTO;
import com.main.timeshareexchangeplatform_backend.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {
    public Image toEntity(ImageDTO imageDTO){
        Image entity= new Image();
        entity.setImage_id(imageDTO.getImage_id());
        entity.setImage_url(imageDTO.getImage_url());
        entity.setDestination(imageDTO.getDes());
        return entity;
    }

    public ImageDTO toDTO(Image image){
        ImageDTO dto = new ImageDTO();
        dto.setImage_id(image.getImage_id());
        dto.setImage_url(image.getImage_url());
        dto.setDes(image.getDestination());
        return dto;
    }
}
