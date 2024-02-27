package com.main.timeshareexchangeplatform_backend.service.impl;


import com.main.timeshareexchangeplatform_backend.dto.*;

import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.entity.Request;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
    public class TimeshareImp implements ITimeshareService {
        @Autowired
        private MyTimeShareRepository myTimeShareRepository;
        @Autowired
        TimeshareConverter timeshareConverter;
        @Autowired
        TimeshareRepository timeshareRepository;
        public List<TimeshareDTO> showListTimeShare(){
             List<Timeshare> timeshares = myTimeShareRepository.showListTimeShare();
            return timeshareConverter.convertToAccountPlaylistDTOList(timeshares);

        }

    @Override
    public TimeshareRespone getTimeshareDetails(UUID timeshareId) {
        Object result = myTimeShareRepository.findTimeshareDetails(timeshareId);

        // Chuyển đổi Object thành TimeshareRespone
        TimeshareRespone timeshareRespone = convertToObject(result);

        return timeshareRespone;
    }

    @Override
    public TimeshareRespone getTimeshareByUserId(UUID userId) {
        Object result = myTimeShareRepository.findTimeshareDetailbyUserId(userId);

        // Chuyển đổi Object thành TimeshareRespone, bạn có thể thực hiện phần này theo cách bạn muốn
        TimeshareRespone timeshareRespone = convertToObject(result);

        return timeshareRespone;
    }

    @Override
    public Timeshare getReferenceById(UUID id) {
        return timeshareRepository.getReferenceById(id);
    }

    @Override
    public List<ResponseTimeshare> findTimeshareByName(String name) {
        List<ResponseTimeshare> timeshares = timeshareRepository.findTimeshareByName(name).stream()
                .map(timeshareConverter::toRespone).collect(Collectors.toList());

        return timeshares;
    }

    @Override
    public TimeshareDTO addTimeshare(TimeshareDTO timeshareDTO){
//            timeshareDTO.setTimeshare_id(UUID.fromString("9d870e8c-0b4f-4e78-a9bf-16a45e7a42e1"));
//            Timeshare timeshare= timeshareConverter.toEntity(timeshareDTO);
//            timeshare.setName(timeshare.getName());
//            timeshare.setDate_end(timeshare.getDate_end());
//            timeshare.setDate_start(timeshare.getDate_start());
//            timeshare.setDestination(timeshare.getDestination());
//            timeshare.setCity(timeshare.getCity());
//            timeshare.setNights(
//                    long nights = ChronoUnit.DAYS.between( timeshare.setDate_start(timeshare.getDate_start());, date_end););
//            timeshare.setPostBy(timeshare.getPostBy());
//            timeshare.getDescription(timeshare.setDescription());
            TimeshareDTO result=new TimeshareDTO();
        return result;
    }
    private String generateUniqueRequestCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        // Concatenate the formatted date and time
        return "RES" + formattedDateTime;
    }

    private TimeshareRespone convertToObject(Object result) {
        if (result == null) {
            return null;
        }

        Object[] row = (Object[]) result;

        // Extract các giá trị từ mảng row
        String timeshare_id = (String) row[0];
        Date date_end = (Date) row[1];
        Date date_start = (Date) row[2];
        String description = (String) row[3];
        boolean exchance = (boolean) row[4];
        String image_url= (String) row[5];
        String name=(String) row[6];
        int nights = (int) row[7];
        long price = (long) row[8];
        boolean status = (boolean) row[9];
        String city=(String) row[12];
        String post_by = (String) row[11];

        DestinationDTO destinationDTO= new DestinationDTO();
        destinationDTO.setDestinationId((String) row[10]);
        destinationDTO.setAddress((String) row[13]);
        destinationDTO.setBranch((String) row[14]);
        destinationDTO.setCity((String) row[15]);
        destinationDTO.setCountry((String) row[16]);
        destinationDTO.setDescription((String) row[17]);
        destinationDTO.setName((String) row[18]);

        RoomtypeDTO roomtypeDTO= new RoomtypeDTO();
        roomtypeDTO.setRoomtypeId((String) row[19]);
        roomtypeDTO.setBath((int) row[20]);
        roomtypeDTO.setBed((int) row[21]);
        roomtypeDTO.setEntertaiment((String) row[22]);
        roomtypeDTO.setFeature((String) row[23]);
        roomtypeDTO.setKitchen((String) row[24]);
        roomtypeDTO.setName((String) row[25]);
        roomtypeDTO.setPolicies((String) row[26]);
        roomtypeDTO.setRoomview((String) row[27]);
        roomtypeDTO.setSleeps((int) row[28]);

        // Tạo đối tượng TimeshareRespone và set giá trị
        TimeshareRespone timeshareRespone = new TimeshareRespone();
        timeshareRespone.setTimeshare_id(timeshare_id);
        timeshareRespone.setDate_end(date_end);
        timeshareRespone.setDate_start(date_start);
        timeshareRespone.setDescription(description);
        timeshareRespone.setExchance(exchance);
        timeshareRespone.setImage_url(image_url);
        timeshareRespone.setName(name);
        timeshareRespone.setNights(nights);
        timeshareRespone.setPrice(price);
        timeshareRespone.setStatus(status);
        timeshareRespone.setPost_by(post_by);
        timeshareRespone.setCity(city);
        timeshareRespone.setDes(destinationDTO);
        timeshareRespone.setRoom(roomtypeDTO);
        return timeshareRespone;
    }

}
