package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TimeshareRepository extends JpaRepository<Timeshare, UUID> {

    @Query(value = "SELECT * FROM TIMESHARE t WHERE t.name = :name", nativeQuery = true)
    List<Timeshare> findTimeshareByName(@Param("name") String name);

    @Query(value = "SELECT * FROM TIMESHARE t WHERE t.owner = :owner", nativeQuery = true)
    List<Timeshare> findAllTimshareByUserId(@Param("owner") UUID owner);
}
