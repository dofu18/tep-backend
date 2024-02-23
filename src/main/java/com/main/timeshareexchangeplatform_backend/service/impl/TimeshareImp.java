package com.main.timeshareexchangeplatform_backend.service.impl;


import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;

import com.main.timeshareexchangeplatform_backend.dto.DestinationDTO;
import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Object result = myTimeShareRepository.findTimeshareDetails(userId);

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

//    private TimeshareRespone convertToObject(Object result) {
//        if (result == null) {
//            return null;
//        }
//
//        Object[] row = (Object[]) result;
//
//        // Extract các giá trị từ mảng row
//
//        UUID timeshare_id = (UUID) row[0];
//        String address = (String) row[1];
//        Date date_end = (Date) row[2];
//        Date date_start = (Date) row[3];
//        String description = (String) row[4];
//        boolean exchance = (boolean) row[5];
//        int nights = (int) row[6];
//        double price = (double) row[7];
//        boolean status = (boolean) row[8];
//        UUID destination_id = (UUID) row[9];
//        UUID post_by = (UUID) row[9];
//        String address1 = (String) row[11];
//        String branch = (String) row[12];
//        String city = (String) row[13];
//        String country = (String) row[14];
//        String description1 = (String) row[15];
//        String name = (String) row[16];
//        int bath = (int) row[17];
//        String entertainment = (String) row[18];
//        String features = (String) row[19];
//        String kitchen = (String) row[20];
//        String name2 = (String) row[21];
//        String policies = (String) row[22];
//        String room_view = (String) row[23];
//        int sleeps = (int) row[24];
//
//
//
//        DestinationDTO destinationDTO= new DestinationDTO();
//        destinationDTO.setDestinationId((UUID) row[10]);
//        destinationDTO.setAddress((String) row[12]);
//        destinationDTO.setBranch((String) row[13]);
//        destinationDTO.setCity((String) row[14]);
//        destinationDTO.setCountry((String) row[15]);
//        destinationDTO.setDescription((String) row[16]);
//        destinationDTO.setName((String) row[17]);
//
//        RoomtypeDTO roomtypeDTO= new RoomtypeDTO();
//        roomtypeDTO.setBath((int) row[18]);
//        roomtypeDTO.setBed((int) row[19]);
//        roomtypeDTO.setEntertaiment((String) row[20]);
//        roomtypeDTO.setFeature((String) row[21]);
//        roomtypeDTO.setKitchen((String) row[22]);
//        roomtypeDTO.setName((String) row[23]);
//        roomtypeDTO.setPolicies((String) row[24]);
//        roomtypeDTO.setRoomview((String) row[25]);
//        roomtypeDTO.setSleeps((int) row[26]);
//
//
//        // Tạo đối tượng TimeshareRespone và set giá trị
//        TimeshareRespone timeshareRespone = new TimeshareRespone();
//        timeshareRespone.setTimeshare_id(timeshare_id);
//        timeshareRespone.setDate_end(date_end);
//        timeshareRespone.setDate_start(date_start);
//        timeshareRespone.setDescription(description);
//        timeshareRespone.setExchance(exchance);
//        timeshareRespone.setImage_url(image_url);
//        timeshareRespone.setName(name);
//        timeshareRespone.setNights(nights);
//        timeshareRespone.setPrice(price);
//        timeshareRespone.setStatus(status);
//        timeshareRespone.setPost_by(post_by);
////        timeshareRespone.setBath(bath);
////        timeshareRespone.setEntertainment(entertainment);
////        timeshareRespone.setFeatures(features);
////        timeshareRespone.setKitchen(kitchen);
////        timeshareRespone.setName2(name2);
////        timeshareRespone.setPolicies(policies);
////        timeshareRespone.setRoom_view(room_view);
////        timeshareRespone.setSleeps(sleeps);
//
//        timeshareRespone.setDes(destinationDTO);
//        timeshareRespone.setRoom(roomtypeDTO);
//        return timeshareRespone;
//    }
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
    String post_by = (String) row[11];

    DestinationDTO destinationDTO= new DestinationDTO();
    destinationDTO.setDestinationId((String) row[10]);
    destinationDTO.setAddress((String) row[12]);
    destinationDTO.setBranch((String) row[13]);
    destinationDTO.setCity((String) row[14]);
    destinationDTO.setCountry((String) row[15]);
    destinationDTO.setDescription((String) row[16]);
    destinationDTO.setName((String) row[17]);

    RoomtypeDTO roomtypeDTO= new RoomtypeDTO();
    roomtypeDTO.setBath((int) row[18]);
    roomtypeDTO.setBed((int) row[19]);
    roomtypeDTO.setEntertaiment((String) row[20]);
    roomtypeDTO.setFeature((String) row[21]);
    roomtypeDTO.setKitchen((String) row[22]);
    roomtypeDTO.setName((String) row[23]);
    roomtypeDTO.setPolicies((String) row[24]);
    roomtypeDTO.setRoomview((String) row[25]);
    roomtypeDTO.setSleeps((int) row[26]);

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

    timeshareRespone.setDes(destinationDTO);
    timeshareRespone.setRoom(roomtypeDTO);
    return timeshareRespone;
}

}
