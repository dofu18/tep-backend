package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.dto.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.hibernate.validator.cfg.defs.UUIDDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface TimeshareRepository extends JpaRepository<Timeshare, UUID> {

    @Query(value = "SELECT * FROM TIMESHARE t WHERE t.name = :name", nativeQuery = true)
    List<Timeshare> findTimeshareByName(@Param("name") String name);

    @Query(value = "SELECT * FROM TIMESHARE t WHERE t.owner = :owner", nativeQuery = true)
    List<Timeshare> findAllTimshareByUserId(@Param("owner") UUID owner);

    @Query(value = "SELECT * FROM TIMESHARE t WHERE t.timeshare_id = :timeshare_id", nativeQuery = true)
    Timeshare findTimshareByTimeshareId(@Param("timeshare_id") UUID timeshare_id);

    @Query(value = "SELECT * FROM Timeshare t WHERE t.status = 1 AND t.city = :city", nativeQuery = true)
    List<Timeshare> findTimshareByCity(@Param("city") String city);

    @Query(value = "SELECT * FROM Timeshare t WHERE t.status = 1 AND t.owner <> :owner", nativeQuery = true)
    List<Timeshare> getAllTimeshareTrueAndExceptOwner(@Param("owner") UUID owner);

    List<Timeshare> findByDateEndBefore(LocalDate currentDate);

//    @Query(value = "SELECT * FROM Timeshare WHERE timeshare_id = :id", nativeQuery = true)
//    Timeshare findTimeshareByTimeshareId(@Param("id") UUID id);
    @Query("SELECT t FROM Timeshare t WHERE t.createDate >= :startDate AND t.createDate <= :endDate")
    List<Timeshare> findTimesharesCreatedWithinLast30Days(LocalDate startDate, LocalDate endDate);

    @Query(value = " select * from timeshare where status = 0", nativeQuery = true )
    List<Timeshare> showListTimeShareInactive();
}
