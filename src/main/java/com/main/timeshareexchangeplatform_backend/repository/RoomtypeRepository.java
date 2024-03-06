package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.dto.RoomtypeDTO;
import com.main.timeshareexchangeplatform_backend.entity.Roomtype;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface RoomtypeRepository extends JpaRepository <Roomtype, UUID>{
    @Query(value = " select * from timeshare", nativeQuery = true )
    List<Timeshare> showListTimeShare();

    @Query(value = "select * from roomtype where timeshare_id=  :timeshare_id", nativeQuery = true)
    RoomtypeDTO getRoomtypeByTimeshareId(@Param("timeshare_id") UUID timeshare_id);
}
