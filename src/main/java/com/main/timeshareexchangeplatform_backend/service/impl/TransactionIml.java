package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.BookingConverter;
import com.main.timeshareexchangeplatform_backend.converter.ServicePackConverter;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.converter.TransactionConverter;
import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.TransactionDTO;
import com.main.timeshareexchangeplatform_backend.dto.TransactionResponse;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.Transaction_history;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.repository.ServicePackRepository;
import com.main.timeshareexchangeplatform_backend.repository.TimeshareRepository;
import com.main.timeshareexchangeplatform_backend.repository.TransactionRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import com.main.timeshareexchangeplatform_backend.service.IServicePackService;
import com.main.timeshareexchangeplatform_backend.service.ITimeshareService;
import com.main.timeshareexchangeplatform_backend.service.ITransactionSevice;
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
public class TransactionIml implements ITransactionSevice {
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ITransactionSevice iTransactionSevice;
    @Autowired
    VNPayService vnPayService;
    @Autowired
    ServicePackConverter servicePackConverter;
    @Autowired
    IServicePackService ServicePackService;
    @Autowired
    ServicePackRepository servicePackRepository;

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        UUID serviceid = transactionDTO.getService_id();

        Transaction_history transaction = transactionConverter.toEntity(transactionDTO);

        Transaction_history existingTransaction = transactionRepository.findByTransactionCode(transactionDTO.getTransaction_code());
        if (existingTransaction == null){
            //luu đơn hàng vào db
            transaction.setTransactionCode(generateUniqueTransactionCode());
            transaction.setTransaction_fee(transaction.getTransaction_fee());
            transaction.setTransaction_time(LocalDateTime.now());


            transaction = transactionRepository.save(transaction);

            Transaction_history transactionservice = new Transaction_history();
            Service_pack servicePack = servicePackRepository.findById(serviceid).orElse(null);
            if (servicePack != null) {
                transactionservice.setServicePack(servicePack);
            }

            TransactionDTO result = transactionConverter.toDTO(transaction);
            return result;
        }

        return null;
    }

    @Override
    public List<TransactionResponse> getAllTransactionByUserId(UUID userid) {
        List<Transaction_history> transactionsEntity = transactionRepository.findAllTransactionByUserId(userid);
        List<TransactionResponse> bookingRespones = transactionsEntity.stream().map(transactionConverter::toRespone).collect(Collectors.toList());

        return bookingRespones;
    }

    @Override
    public List<TransactionResponse> getServicePackByUserId(UUID userid) {
        List<Transaction_history> transactionHistories = transactionRepository.findAllTransactionByUserId(userid);
        List<TransactionResponse> transactionResponses = transactionHistories.stream().map(transactionConverter::toRespone).collect(Collectors.toList());

        return transactionResponses;
    }

    private String generateUniqueTransactionCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        // Concatenate the formatted date and time
        return "TSC" + formattedDateTime; // You can customize the prefix as needed
    }
}
