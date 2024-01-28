package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;

public interface IBookingService {
    BookingModel addBooking(BookingModel bookingModel);
    boolean isTimeshareBooked(Integer timeshareId);
}
