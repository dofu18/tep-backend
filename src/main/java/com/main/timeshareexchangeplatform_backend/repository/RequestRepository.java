package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {

}

