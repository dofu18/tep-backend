package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class TimeshareConverter {
    public static TimeshareDTO toDTO(Timeshare timeshare) {
        if (timeshare == null) {
            return null;
        }

        TimeshareDTO dto = new TimeshareDTO();
        dto.setTimeshare_id(timeshare.getTimeshare_id());
        dto.setDate_start(timeshare.getDate_start());
        dto.setDate_end(timeshare.getDate_end());
        dto.setNights(timeshare.getNights());
        dto.setPrice(timeshare.getPrice());
        dto.setStatus(timeshare.isStatus());
        dto.setAddress(timeshare.getAddress());
        dto.setPost_by(timeshare.getUser().getUser_id());  // Assuming there is a User entity in Timeshare
        dto.setDestination_id(timeshare.getDestination().getDestination_id());  // Assuming there is a Destination entity in Timeshare
        dto.setDescription(timeshare.getDescription());
        return dto;
    }

    public static Timeshare toEntity(TimeshareDTO dto, User postedBy, Destination destination) {
        if (dto == null) {
            return null;
        }

        Timeshare timeshare = new Timeshare();
        timeshare.setTimeshare_id(dto.getTimeshare_id());
        timeshare.setDate_start(dto.getDate_start());
        timeshare.setDate_end(dto.getDate_end());
        timeshare.setNights(dto.getNights());
        timeshare.setPrice(dto.getPrice());
        timeshare.setStatus(dto.isStatus());
        timeshare.setAddress(dto.getAddress());
        timeshare.setUser(postedBy);
        timeshare.setDestination(destination);
        timeshare.setDescription(dto.getDescription());
        return timeshare;
    }
    public List<TimeshareDTO> convertToAccountPlaylistDTOList(List<Timeshare> StudentList) {
        List<TimeshareDTO> StudentDTOList = new ArrayList<>();
        for (Timeshare accountPlaylist : StudentList) {
            StudentDTOList.add(toDTO(accountPlaylist));
        }
        return StudentDTOList;
    }
}
