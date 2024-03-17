package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;

import java.util.List;
import java.util.UUID;

public interface IBookingService {
    BookingModel addBooking(BookingModel bookingModel);

    List<BookingResponse> getAllBookingByUserId(UUID userid);

    List<BookingResponse> findAll();
}
