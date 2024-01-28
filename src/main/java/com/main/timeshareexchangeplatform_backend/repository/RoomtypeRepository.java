package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Roomtype;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomtypeRepository extends JpaRepository <Roomtype, Integer>{
    @Query(value = " select * from timeshare", nativeQuery = true )
    List<Timeshare> showListTimeShare();
}
