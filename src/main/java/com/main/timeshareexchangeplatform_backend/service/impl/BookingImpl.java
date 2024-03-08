package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.BookingConverter;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import com.main.timeshareexchangeplatform_backend.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingImpl implements IBookingService {
    @Autowired
    BookingConverter bookingConverter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    IBookingService bookingService;
    @Autowired
    VNPayService vnPayService;
    @Autowired
    TimeshareConverter timeshareConverter;
    @Autowired
    ITimeshareService timeshareService;
    @Autowired
    TimeshareRepository timeshareRepository;


    @Override
    public BookingModel addBooking(BookingModel bookingModel) {

        UUID timeshareId = bookingModel.getTimeshare_id();
//        ResponseTimeshare responseTimeshare = new ResponseTimeshare();
//        ResponseTimeshare result = timeshareConverter.toRespone(timeshareService.getReferenceById(timeshareId));

        Booking booking = bookingConverter.toEntity(bookingModel);
//        Timeshare timeshare = timeshareConverter.

        Booking existingBooking = bookingRepository.findByBookingCode(bookingModel.getBookingCode());
        if (existingBooking == null){
            //luu đơn hàng vào db
            booking.setBookingCode(generateUniqueBookingCode());
            booking.setTotal(booking.getTotal());
            booking.setSuccess_date(LocalDate.now());
            booking.setFullname(booking.getFullname());
            booking.setAdults(booking.getAdults());
            booking.setChildren(booking.getChildren());
            booking.setCity(booking.getCity());
            booking.setCountry(booking.getCountry());
            booking.setPayment_method(booking.getPayment_method());
            booking.setPostal_code(booking.getPostal_code());
            booking.setState(booking.getState());
            booking.setStreet(booking.getStreet());
            booking.setTelephone(booking.getTelephone());

            booking.setStatus(false);// Mặc định là đã book

            booking = bookingRepository.save(booking);

            Booking timeshareBooking = new Booking();
            Timeshare timeshare = timeshareRepository.findById(timeshareId).orElse(null);
            if (timeshare != null) {
                timeshare.setStatus(false);
                timeshareBooking.setTimeshare(timeshare);
            }

            BookingModel result = bookingConverter.toDTO(booking);
            return result;
        }

        return null;
    }

    @Override
    public List<BookingResponse> getAllBookingByUserId(UUID userid) {
        List<Booking> bookingsEntity = bookingRepository.findAllBookingByUserId(userid);
        List<BookingResponse> bookingRespones = bookingsEntity.stream().map(bookingConverter::toRespone).collect(Collectors.toList());

        return bookingRespones;
    }

    private String generateUniqueBookingCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        // Concatenate the formatted date and time
        return "BOOK" + formattedDateTime; // You can customize the prefix as needed
    }
}
