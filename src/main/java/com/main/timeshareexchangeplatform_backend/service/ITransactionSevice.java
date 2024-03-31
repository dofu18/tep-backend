package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.TransactionDTO;
import com.main.timeshareexchangeplatform_backend.dto.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface ITransactionSevice {
    TransactionDTO addTransaction(TransactionDTO transactionDTO);

    List<TransactionResponse> getAllTransactionByUserId(UUID userid);

    List<TransactionResponse> getServicePackByUserId(UUID userid);

    long getTotalSum();

    long getTotalSumByUserId(UUID userId);
}
