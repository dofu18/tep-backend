package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserDTO;
import com.main.timeshareexchangeplatform_backend.dto.UserModel;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    static UserModel toResponse(User entity) {
        UserModel dto = new UserModel();
        dto.setUser_id(entity.getUser_id());
        dto.setUser_name(entity.getUsername());

        return dto;
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setUser_id(user.getUser_id());
        dto.setDob(String.valueOf(user.getDob()));
        dto.setGender(user.getGender());
        dto.setStatus(user.isStatus());
        dto.setEmail(user.getEmail());
        dto.setFullname(user.getFullname());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setUser_name(user.getUsername());
        dto.setCreateDate(user.getCreateDate());

        return dto;
    }

    public User toEntity (UserDTO userDTO) {
        User entity = new User();

        entity.setUser_id(userDTO.getUser_id());
        entity.setDob(LocalDate.parse(userDTO.getDob()));
        entity.setGender(userDTO.getGender());
        entity.setStatus(userDTO.isStatus());
        entity.setEmail(userDTO.getEmail());
        entity.setFullname(userDTO.getFullname());
        entity.setPhone(userDTO.getPhone());
        entity.setRole(userDTO.getRole());
        entity.setUsername(userDTO.getUser_name());
        entity.setCreateDate(userDTO.getCreateDate());

        return entity;
    }

    public List<UserDTO> convertToUserDTOList(List<User> UserList) {
        List<UserDTO> UserDTOList = new ArrayList<>();
        for (User accountPlaylist : UserList) {
            UserDTOList.add(toDTO(accountPlaylist));
        }
        return UserDTOList;
    }
}
