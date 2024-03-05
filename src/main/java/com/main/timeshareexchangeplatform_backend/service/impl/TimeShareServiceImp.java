package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.dto.*;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
    public class TimeShareServiceImp implements TimeShareService {
        @Autowired
        private MyTimeShareRepository myTimeShareRepository;
        @Autowired
    TimeshareConverter timeshareConverter;
        public List<TimeshareDTO> showListTimeShare(){
             List<Timeshare> timeshares = myTimeShareRepository.showListTimeShare();
            return timeshareConverter.convertToAccountPlaylistDTOList(timeshares);

        }

    @Override
    public TimeshareRespone getTimeshareDetails(UUID timeshareId) {
        Object result = myTimeShareRepository.findTimeshareDetails(timeshareId);

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
    public Boolean createTimeshare(TimeshareDTO timeshareRespone) {
            Timeshare timeshare= timeshareConverter.toEntity(timeshareRespone);

        timeshare.setCity(timeshareRespone.getCity());
        timeshare.setDate_end(timeshareRespone.getDate_end());
        timeshare.setDate_start(timeshareRespone.getDate_start());
        timeshare.setDescription(timeshareRespone.getDescription());
        timeshare.setExchange(timeshareRespone.isExchange());

        timeshare.setImage_url(timeshareRespone.getImage_url());
        timeshare.setName(timeshareRespone.getName());
        timeshare.setPostBy(timeshare.getPostBy());
        timeshare.setDestination(timeshare.getDestination());
        // Calculate the number of nights
        int night= Math.toIntExact(ChronoUnit.DAYS.between(timeshareRespone.getDate_start(), timeshareRespone.getDate_end()));
        timeshare.setNights(night);
        timeshare.setPrice(timeshareRespone.getPrice());
        myTimeShareRepository.save(timeshare);
        if(myTimeShareRepository.findById(timeshareRespone.getTimeshare_id()) !=null)
            return true;
        else return false;
    }
//     request.setTimeshare_response(timeshareService.getReferenceById(requestModel.getTimeshare_response_id()));
//            request.setResponseby(request.getTimeshare_response().getPostBy());
//            request.setTimeshare_request(timeshareService.getReferenceById(requestModel.getTimeshare_request_id()));

    @Override
    public TimeshareRespone getTimeshareByUserId(UUID userId) {
        Object result = myTimeShareRepository.findTimeshareDetailbyUserId(userId);

        // Chuyển đổi Object thành TimeshareRespone, bạn có thể thực hiện phần này theo cách bạn muốn
        TimeshareRespone timeshareRespone = convertToObject(result);

        return timeshareRespone;
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
        timeshareRespone.setOwner(post_by);

        timeshareRespone.setDes(destinationDTO);
        timeshareRespone.setRoom(roomtypeDTO);
        return timeshareRespone;
    }

}
