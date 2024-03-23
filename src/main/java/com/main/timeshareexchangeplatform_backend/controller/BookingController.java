package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.ResponseTimeshare;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.vnpay.PaymentResponse;
import com.main.timeshareexchangeplatform_backend.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/booking")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/payment")
    public PaymentResponse makeBooking(@RequestBody BookingModel bookingModel) throws UnsupportedEncodingException {
        BookingModel addedBooking = bookingService.addBooking(bookingModel);

        if (addedBooking != null) {
            long price = addedBooking.getTotal();
            String paymentUrl = vnPayService.makePayment(price, null, addedBooking.getBookingCode());
            String code = "00";
            String message = "Success";
            return new PaymentResponse(code, message, paymentUrl);
        } else {
            return new PaymentResponse("01", "Fail", null);
        }
    }

    @GetMapping(value = "/getBookingByUserId/{id}")
    public List<BookingResponse> getAllBookingByUserId(@PathVariable UUID id) {
        return bookingService.getAllBookingByUserId(id);

    }

    @GetMapping("/viewAll")
    public List<BookingResponse> findAll() {

        return bookingService.findAll();
    }

    @GetMapping("/total-booking-timeshare")
    public long getTotalSum() {
        return bookingService.getTotalSum();
    }

    @GetMapping("/total-booking-even-user/{userId}")
    public long getTotalSumByUserId(@PathVariable UUID userId) {
        return bookingService.getTotalSumByUserId(userId);
    }
}
