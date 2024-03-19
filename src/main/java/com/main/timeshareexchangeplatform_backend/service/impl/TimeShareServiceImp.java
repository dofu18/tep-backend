package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.dto.*;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.entity.Roomtype;
import com.main.timeshareexchangeplatform_backend.repository.DestinationRepository;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        @Autowired
        DestinationRepository destinationRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        TimeshareRepository timeshareRepository;

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
    public TimeshareDTO getImageById(UUID timeshare_id) {
        return null;
    }

    @Override
    public String deactiveTimeshare(UUID timeshareId) {
        Timeshare timeshare = timeshareRepository.findTimshareByTimeshareId(timeshareId);

        if (timeshare != null) {
            if (timeshare.isStatus()) {
                timeshare.setStatus(false);
                timeshareRepository.save(timeshare);
                return "Successfully deactive timeshare";
            } else {
                return "Timeshare was deactive already";
            }
        }

        return "No timeshare found";
    }

    @Override
    public String updateTimeshare(TimeshareDTO timeshareDTO) {
        Timeshare entity = timeshareRepository.getReferenceById(timeshareDTO.getTimeshare_id());
        if (entity.getTimeshare_id() == timeshareDTO.getTimeshare_id()) {

            entity.setCity(timeshareDTO.getCity());
            entity.setDateEnd(timeshareDTO.getDate_end());
            entity.setDate_start(timeshareDTO.getDate_start());
            entity.setDescription(timeshareDTO.getDescription());
            entity.setExchange(timeshareDTO.isExchange());
            entity.setImage_url(timeshareDTO.getImage_url());
            entity.setName(timeshareDTO.getName());
            entity.setNights(timeshareDTO.getNights());
            entity.setPrice(timeshareDTO.getPrice());
            entity.setStatus(timeshareDTO.isStatus());
            entity.setTimeshareCode(timeshareDTO.getTimeshare_code());
            entity.setDestination(destinationRepository.getReferenceById(timeshareDTO.getDestination_id()));
            entity.setPostBy(userRepository.getReferenceById(timeshareDTO.getOwner()));

            timeshareRepository.save(entity);
            return "Update Successfully";
        }
        return "Fail to update";
    }

//    @Override
//    public List<Timeshare> getTimesharesCreatedWithinLast30Days() {
//        LocalDate startDate = LocalDate.now().minusDays(30);
//        return timeshareRepository.findTimesharesCreatedWithinLast30Days(startDate);
//    }

//    @Override
//    public String updateTimeshare(TimeshareDTO timeshareDTO) {
//        Timeshare entity = myTimeShareRepository.getReferenceById(timeshareDTO.getTimeshare_id());
//        if (entity.getTimeshare_id() == timeshareDTO.getTimeshare_id()) {
//
//            entity.setCity(timeshareDTO.getCity());
//            entity.setDateEnd(timeshareDTO.getDate_end());
//            entity.setDate_start(timeshareDTO.getDate_start());
//            entity.setDescription(timeshareDTO.getDescription());
//            entity.setExchange(timeshareDTO.isExchange());
//            entity.setImage_url(timeshareDTO.getImage_url());
//            entity.setName(timeshareDTO.getName());
//            entity.setNights(timeshareDTO.getNights());
//            entity.setPrice(timeshareDTO.getPrice());
//            entity.setStatus(timeshareDTO.isStatus());
//            entity.setTimeshareCode(timeshareDTO.getTimeshare_code());
//            entity.setDestination(destinationRepository.getReferenceById(timeshareDTO.getDestination_id()));
//            entity.setPostBy(userRepository.getReferenceById(timeshareDTO.getOwner()));
//
//            myTimeShareRepository.save(entity);
//            return "Update Successfully";
//        }
//        return "Fail to update";
//    }


    @Override
    public ResponseTimeshare createTimeshare(TimeshareDTO timeshareRespone) {
            Timeshare timeshare= timeshareConverter.toEntity(timeshareRespone);

        timeshare.setTimeshareCode(generateUniqueTimeshareCode());
        timeshare.setCity(timeshareRespone.getCity());
        timeshare.setDateEnd(timeshareRespone.getDate_end());
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
        ResponseTimeshare timeshareDTO= timeshareConverter.toRespone(timeshare);
        return timeshareDTO;
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

    private String generateUniqueTimeshareCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        // Concatenate the formatted date and time
        return "TS" + formattedDateTime;
    }

}
