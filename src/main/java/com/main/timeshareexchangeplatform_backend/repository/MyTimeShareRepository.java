package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MyTimeShareRepository extends JpaRepository<Timeshare, UUID> {
    @Query(value = " select * from timeshare", nativeQuery = true )
    List<Timeshare> showListTimeShare();

    @Query(value = " select * from timeshare \n"+
            "where owner = :userId ", nativeQuery = true)
     List<Timeshare> showAllTimeshareUser(@Param("userId") UUID userId);
    @Query(value = "SELECT \n" +
            "    t.*,\n" +
            "    d.address,\n" +
            "    d.branch,\n" +
            "    d.city,\n" +
            "    d.country,\n" +
            "    d.description,\n" +
            "    d.name,\n" +
            "    r.roomtype_id,\n"+
            "    r.bath,\n" +
            "    r.bed,\n" +
            "    r.entertainment,\n" +
            "    r.features,\n" +
            "    r.kitchen,\n" +
            "    r.name,\n" +
            "    r.policies,\n" +
            "    r.room_view,\n" +
            "    r.sleeps\n" +
            "FROM \n" +
            "    timeshare AS t\n" +
            "JOIN \n" +
            "    destination AS d ON t.destination_id = d.destination_id\n" +
            "JOIN \n" +
            "    roomtype AS r ON t.timeshare_id = r.timeshare_id\n" +
            "WHERE \n" +
            "    t.timeshare_id = :timeshareId", nativeQuery = true)
    Timeshare findTimeshareDetails(@Param("timeshareId") UUID timeshareId);

    @Query(value ="SELECT \n" +
            "    t.*,\n" +
            "    d.address,\n" +
            "    d.branch,\n" +
            "    d.city,\n" +
            "    d.country,\n" +
            "    d.description,\n" +

            "    d.name,\n" +
            "    r.roomtype_id,\n"+
            "    r.bath,\n" +
            "    r.bed,\n" +
            "    r.entertainment,\n" +
            "    r.features,\n" +
            "    r.kitchen,\n" +
            "    r.name,\n" +
            "    r.policies,\n" +
            "    r.room_view,\n" +
            "    r.sleeps\n" +
            "FROM \n" +
            "    timeshare AS t\n" +
            "JOIN \n" +
            "    destination AS d ON t.destination_id = d.destination_id\n" +
            "JOIN \n" +
            "    roomtype AS r ON t.timeshare_id = r.timeshare_id\n" +

            "where t.owner = :userId ", nativeQuery = true)
    Object findTimeshareDetailbyUserId(@Param("userId") UUID userId);



}
