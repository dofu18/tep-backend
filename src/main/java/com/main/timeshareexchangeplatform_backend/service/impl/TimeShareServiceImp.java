package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.dto.TimeshareRespone;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TimeshareRespone convertToObject(Object result) {
        if (result == null) {
            return null;
        }

        Object[] row = (Object[]) result;

        // Extract các giá trị từ mảng row
        UUID timeshare_id = (UUID) row[0];
        String address = (String) row[1];
        Date date_end = (Date) row[2];
        Date date_start = (Date) row[3];
        String description = (String) row[4];
        boolean exchance = (boolean) row[5];
        int nights = (int) row[6];
        long price = (long) row[7];
        boolean status = (boolean) row[8];
        int destination_id = (int) row[9];
        UUID post_by = (UUID) row[10];
        String address1 = (String) row[11];
        String branch = (String) row[12];
        String city = (String) row[13];
        String country = (String) row[14];
        String description1 = (String) row[15];
        String name = (String) row[16];
        int bath = (int) row[17];
        String entertainment = (String) row[18];
        String features = (String) row[19];
        String kitchen = (String) row[20];
        String name2 = (String) row[21];
        String policies = (String) row[22];
        String room_view = (String) row[23];
        int sleeps = (int) row[24];

        // Tạo đối tượng TimeshareRespone và set giá trị
        TimeshareRespone timeshareRespone = new TimeshareRespone();
        timeshareRespone.setTimeshare_id(timeshare_id);
        //timeshareRespone.setAddress(address);
        timeshareRespone.setDate_end(date_end);
        timeshareRespone.setDate_start(date_start);
        timeshareRespone.setDescription(description);
        timeshareRespone.setExchance(exchance);
        timeshareRespone.setNights(nights);
        timeshareRespone.setPrice(price);
        timeshareRespone.setStatus(status);
       // timeshareRespone.setDestination_id(destination_id);
        timeshareRespone.setPost_by(post_by);
        //timeshareRespone.setAddress(address1);
        ///timeshareRespone.setBranch(branch);
//        timeshareRespone.setCity(city);
//        timeshareRespone.setCountry(country);
//        timeshareRespone.setDescription1(description1);
        timeshareRespone.setName(name);
//        timeshareRespone.setBath(bath);
//        timeshareRespone.setEntertainment(entertainment);
//        timeshareRespone.setFeatures(features);
//        timeshareRespone.setKitchen(kitchen);
//        timeshareRespone.setName2(name2);
//        timeshareRespone.setPolicies(policies);
//        timeshareRespone.setRoom_view(room_view);
//        timeshareRespone.setSleeps(sleeps);

        return timeshareRespone;
    }

}
