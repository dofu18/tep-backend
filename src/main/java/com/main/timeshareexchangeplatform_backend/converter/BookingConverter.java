package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
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
    @Autowired
    UserConverter userConverter;
    @Autowired
    TimeshareConverter timeshareConverter;

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
        entity.setUser(userRepository.getReferenceById(bookingModel.getUser_id()));
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
        dto.setUser_id(bookingEntity.getUser().getUser_id());
        dto.setTimeshare_id(bookingEntity.getTimeshare().getTimeshare_id());

        return dto;
    }

    public BookingResponse toRespone(Booking entity) {
        BookingResponse dto = new BookingResponse();

        dto.setBooking_id(entity.getBooking_id());
        dto.setBookingCode(entity.getBookingCode());
        dto.setTotal(entity.getTotal());
        dto.setSuccess_date(entity.getSuccess_date());
        dto.setStatus(entity.getStatus());
        dto.setPayment_status(entity.isPayment_status());
        dto.setAdults(entity.getAdults());
        dto.setChildren(entity.getChildren());
        dto.setTelephone(entity.getTelephone());
        dto.setFullname(entity.getFullname());
        dto.setCountry(entity.getCountry());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setPostal_code(entity.getPostal_code());
        dto.setUser_id(userConverter.toDTO(entity.getUser()));
        dto.setTimeshare_id(timeshareConverter.toRespone(entity.getTimeshare()));

        return dto;
    }

    public Booking toResEntity(BookingResponse dto) {
        Booking entity = new Booking();

        entity.setBooking_id(dto.getBooking_id());
        entity.setBookingCode(dto.getBookingCode());
        entity.setTotal(dto.getTotal());
        entity.setSuccess_date(dto.getSuccess_date());
        entity.setStatus(dto.isStatus());
        entity.setPayment_method(dto.getPayment_method());
        entity.setAdults(dto.getAdults());
        entity.setChildren(dto.getChildren());
        entity.setTelephone(dto.getTelephone());
        entity.setCountry(dto.getCountry());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPostal_code(dto.getPostal_code());
        entity.setPayment_method(dto.getPayment_method());
        entity.setUser(userConverter.toEntity(dto.getUser_id()));
        entity.setTimeshare(timeshareConverter.toResEntity(dto.getTimeshare_id()));

        return entity;
    }
}
