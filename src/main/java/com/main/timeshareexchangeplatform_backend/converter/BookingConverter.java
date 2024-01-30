package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MyTimeShareRepository timeshareRepository;

    public Booking toEntity(BookingModel bookingModel) {

        Booking entity = new Booking();
        entity.setBooking_id(bookingModel.getBooking_id());
        entity.setBookingCode(bookingModel.getBookingCode());
        entity.setTotal(bookingModel.getTotal());
        entity.setCreate_date(bookingModel.getCreate_date());
        entity.setSuccess_date(bookingModel.getSuccess_date());
        entity.setStatus(bookingModel.isStatus());
        entity.setUser(userRepository.getReferenceById(bookingModel.getUser_id()));
        entity.setTimeshare(timeshareRepository.getReferenceById(bookingModel.getTimeshare_id()));


        return entity;
    }

    public BookingModel toDTO(Booking bookingEntity) {

        BookingModel dto = new BookingModel();
        dto.setBooking_id(bookingEntity.getBooking_id());
        dto.setBookingCode(bookingEntity.getBookingCode());
        dto.setTotal(bookingEntity.getTotal());
        dto.setCreate_date(bookingEntity.getCreate_date());
        dto.setSuccess_date(bookingEntity.getSuccess_date());
        dto.setStatus(bookingEntity.getStatus());
        dto.setUser_id(bookingEntity.getUser().getUser_id());
        dto.setTimeshare_id(bookingEntity.getTimeshare().getTimeshare_id());

        return dto;
    }
}
