package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, UUID> {
//    boolean isTimeshareBooked(int timeshareId);
//boolean existsByTimeshareId(Integer timeshareId);
//    Booking getReferenceById(UUID id);

    Booking findByBookingCode(String bookingCode);

    @Query(value = "SELECT * FROM BOOKING t WHERE t.user_id = :user_id", nativeQuery = true)
    List<Booking> findAllBookingByUserId(@Param("user_id") UUID user_id);
}
