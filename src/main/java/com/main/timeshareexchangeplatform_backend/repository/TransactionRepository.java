package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import com.main.timeshareexchangeplatform_backend.entity.Transaction_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction_history, UUID> {
    Transaction_history findByTransactionCode(String transactionCode);

    @Query(value = "SELECT * FROM TRANSACTION_HISTORY t WHERE t.user_id = :user_id", nativeQuery = true)
    List<Transaction_history> findAllTransactionByUserId(@Param("user_id") UUID user_id);

}
