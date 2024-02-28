package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import com.main.timeshareexchangeplatform_backend.entity.Transaction_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction_history, UUID> {
    Transaction_history findByTransactionCode(String transactionCode);
}
