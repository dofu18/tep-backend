package com.main.timeshareexchangeplatform_backend.controller;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.TransactionDTO;
import com.main.timeshareexchangeplatform_backend.dto.TransactionResponse;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.repository.TransactionRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.service.ITransactionSevice;
import com.main.timeshareexchangeplatform_backend.vnpay.PaymentResponse;
import com.main.timeshareexchangeplatform_backend.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/service-payment")
public class TransactionController {

    @Autowired
    ITransactionSevice TransactionSevice;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    VNPayService vnPayService;

    @PostMapping("/payment")
    public PaymentResponse makeTransactionPayment(@RequestBody TransactionDTO transactionDTO) throws UnsupportedEncodingException {
        TransactionDTO addedTransaction = TransactionSevice.addTransaction(transactionDTO);

        if (addedTransaction != null) {
            long price = addedTransaction.getTransaction_fee();
            String paymentUrl = vnPayService.makePayment(price, null, addedTransaction.getTransaction_code());
            String code = "00";
            String message = "Success";
            return new PaymentResponse(code, message, paymentUrl);
        } else {
            return new PaymentResponse("01", "Fail", null);
        }
    }

    @GetMapping(value = "/getTransactionByUserId/{id}")
    public List<TransactionResponse> getAllTransactionByUserId(@PathVariable UUID id) {
        return TransactionSevice.getAllTransactionByUserId(id);

    }

//    @GetMapping(value = "/getTransactionByUserId/{id}")
//    public List<TransactionResponse> getAllBookingByUserId(@PathVariable UUID id) {
//        return TransactionSevice.getServicePackByUserId(id);
//
//    }




}
