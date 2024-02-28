package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Request;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {
    @Query(value = "SELECT * FROM REQUEST r WHERE r.response_by = :response_by", nativeQuery = true)
    List<Request> getAllRequestByResponseId(@Param("response_by") UUID response_by);
}

