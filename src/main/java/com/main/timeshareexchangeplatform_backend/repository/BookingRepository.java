package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, UUID> {
//    boolean isTimeshareBooked(int timeshareId);
//boolean existsByTimeshareId(Integer timeshareId);
//    Booking getReferenceById(UUID id);

    Booking findByBookingCode(String bookingCode);
}
