package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyTimeShareRepository extends JpaRepository<Timeshare, Integer> {
    @Query(value = " select * from timeshare", nativeQuery = true )
    List<Timeshare> showListTimeShare();

//    @Query(value = "select * from timeshare")

}
