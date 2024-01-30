package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeshareRepository extends JpaRepository<Timeshare, Integer> {

}
