package com.main.timeshareexchangeplatform_backend.service.impl;


import com.main.timeshareexchangeplatform_backend.dto.*;

import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


        public List<ResponseTimeshare> showListTimeShare(){
            updateTimeshareStatus();
             List<Timeshare> timeshares = myTimeShareRepository.showListTimeShare();
//             updateTimeshareStatus();
            return timeshareConverter.convertListToResponse(timeshares);

        }

    @Override
    public List<TimeshareDTO> getTimesharesCreatedWithinLast30Days() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        List<Timeshare> timeshares = timeshareRepository.findTimesharesCreatedWithinLast30Days(startDate, endDate);
        return timeshareConverter.convertToAccountPlaylistDTOList(timeshares);
    }

    public void updateTimeshareStatus() {
        // Get timeshares with date_end before current date
        List<Timeshare> expiredTimeshares = timeshareRepository.findByDateEndBefore(LocalDate.now());

        // Update status to false for each expired timeshare
        for (Timeshare timeshare : expiredTimeshares) {
            timeshare.setStatus(false);
            timeshare.setTemporary_owner(null);
            timeshareRepository.save(timeshare);
        }
    }

    @Override
    public Timeshare getTimeshareDetails(UUID timeshareId) {
        Timeshare result = myTimeShareRepository.findTimeshareDetails(timeshareId);

        // Chuyển đổi Object thành TimeshareRespone
        TimeshareRespone timeshareRespone=convertToObject(result);
        return result;
    }

    @Override
    public TimeshareRespone getTimeshareByUserId(UUID userId) {
        Object result = myTimeShareRepository.findTimeshareDetailbyUserId(userId);

        // Chuyển đổi Object thành TimeshareRespone, bạn có thể thực hiện phần này theo cách bạn muốn
        TimeshareRespone timeshareRespone = convertToObject(result);

        return timeshareRespone;
    }
    @Override
    public List<TimeshareDTO> getAllTimeshareUser(UUID userId) {
        List<Timeshare> timeshares = myTimeShareRepository.showAllTimeshareUser(userId);
        return timeshareConverter.convertToAccountPlaylistDTOList(timeshares);

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
    public ResponseTimeshare findTimeshareByTimeshareId(UUID timeshare_id) {

            ResponseTimeshare timeshare = timeshareConverter.toRespone(timeshareRepository.findTimshareByTimeshareId(timeshare_id));

            return timeshare;
    }

    @Override
    public List<ResponseTimeshare> findTimeshareByCity(String city) {

        List<ResponseTimeshare> timeshare = timeshareRepository.findTimshareByCity(city).stream()
                .map(timeshareConverter::toRespone).collect(Collectors.toList());
        return timeshare;
    }

    @Override
    public List<ResponseTimeshare> getAllTimeshareTrueExceptOwner(UUID owner) {
        updateTimeshareStatus();
        List<Timeshare> timeshares = timeshareRepository.getAllTimeshareTrueAndExceptOwner(owner);
//             updateTimeshareStatus();
        return timeshareConverter.convertListToResponse(timeshares);
    }

    @Override
    public List<ResponseTimeshare> findAll() {
        List<ResponseTimeshare> timeshareModel = timeshareRepository.findAll().stream().map(timeshareConverter::toRespone)
                .collect(Collectors.toList());

        return timeshareModel;
    }

    @Override
    public List<ResponseTimeshare> showListTimeShareInactive() {
        List<Timeshare> timeshares = timeshareRepository.showListTimeShareInactive();
        return timeshareConverter.convertListToResponse(timeshares);
    }

    @Override
    public List<ResponseTimeshare> getAllTimeshareByUserId(UUID userid) {
        List<Timeshare> timeshareEntity = timeshareRepository.findAllTimshareByUserId(userid);
        List<ResponseTimeshare> timeshareRespones = timeshareEntity.stream().map(timeshareConverter::toRespone).collect(Collectors.toList());

        return timeshareRespones;
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
        String city=(String) row[1];
        Date date_end = (Date) row[2];
        Date date_start = (Date) row[3];
        String description = (String) row[4];
        boolean exchance = (boolean) row[5];
        String image_url= (String) row[6];
        String name=(String) row[7];
        int nights = (int) row[8];
        long price = (long) row[9];
        boolean status = (boolean) row[10];

        String post_by = (String) row[12];

        DestinationDTO destinationDTO= new DestinationDTO();
        destinationDTO.setDestinationId((String) row[11]);
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
        timeshareRespone.setOwner(post_by);
        timeshareRespone.setCity(city);
        timeshareRespone.setDes(destinationDTO);
        timeshareRespone.setRoom(roomtypeDTO);
        return timeshareRespone;
    }

}
