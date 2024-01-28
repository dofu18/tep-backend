package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.vnpay.PaymentResponse;
import com.main.timeshareexchangeplatform_backend.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("booking")
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
            long price = Math.round(addedBooking.getTotal());
            String paymentUrl = vnPayService.makePayment(price, null, addedBooking.getBooking_id());
            String code = "00";
            String message = "Success";
            return new PaymentResponse(code,message,paymentUrl);
        } else {
            return new PaymentResponse("01", "Fail", null);
        }
    }
}
