package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.dto.TransactionDTO;

public interface ITransactionSevice {
    TransactionDTO addTransaction(TransactionDTO transactionDTO);
}
