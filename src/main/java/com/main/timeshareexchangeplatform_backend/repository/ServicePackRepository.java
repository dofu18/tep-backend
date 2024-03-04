package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ServicePackRepository extends JpaRepository<Service_pack, UUID> {
    @Query(value = "select * from service_pack", nativeQuery = true)
   List <Service_pack> showAllSevicePack();
}
