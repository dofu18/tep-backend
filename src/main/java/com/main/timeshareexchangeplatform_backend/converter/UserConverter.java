package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.UserModel;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserModel toResponse(User entity) {
        UserModel dto = new UserModel();
        dto.setUser_id(entity.getUser_id());
        dto.setUser_name(entity.getUser_name());

        return dto;
    }
}
