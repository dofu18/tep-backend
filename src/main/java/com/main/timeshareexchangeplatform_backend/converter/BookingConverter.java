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
        entity.setSuccess_date(bookingModel.getSuccess_date());
        entity.setStatus(bookingModel.isStatus());
        entity.setPayment_method(bookingModel.getPayment_method());
        entity.setAdults(bookingModel.getAdults());
        entity.setChildren(bookingModel.getChildren());
        entity.setTelephone(bookingModel.getTelephone());
        entity.setCountry(bookingModel.getCountry());
        entity.setStreet(bookingModel.getStreet());
        entity.setCity(bookingModel.getCity());
        entity.setState(bookingModel.getState());
        entity.setPostal_code(bookingModel.getPostal_code());
        entity.setPayment_method(bookingModel.getPayment_method());
        //entity.setUser(userRepository.getReferenceById(bookingModel.getUser_id()));
        entity.setTimeshare(timeshareRepository.getReferenceById(bookingModel.getTimeshare_id()));


        return entity;
    }

    public BookingModel toDTO(Booking bookingEntity) {

        BookingModel dto = new BookingModel();
        dto.setBooking_id(bookingEntity.getBooking_id());
        dto.setBookingCode(bookingEntity.getBookingCode());
        dto.setTotal(bookingEntity.getTotal());
        dto.setSuccess_date(bookingEntity.getSuccess_date());
        dto.setStatus(bookingEntity.getStatus());
        dto.setPayment_status(bookingEntity.isPayment_status());
        dto.setAdults(bookingEntity.getAdults());
        dto.setChildren(bookingEntity.getChildren());
        dto.setTelephone(bookingEntity.getTelephone());
        dto.setFullname(bookingEntity.getFullname());
        dto.setCountry(bookingEntity.getCountry());
        dto.setStreet(bookingEntity.getStreet());
        dto.setCity(bookingEntity.getCity());
        dto.setState(bookingEntity.getState());
        dto.setPostal_code(bookingEntity.getPostal_code());
        dto.setPayment_method(bookingEntity.getPayment_method());
        //dto.setUser_id(bookingEntity.getUser().getUser_id());
        dto.setTimeshare_id(bookingEntity.getTimeshare().getTimeshare_id());

        return dto;
    }
}
