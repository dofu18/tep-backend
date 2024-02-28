package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Service_pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicePackRepository extends JpaRepository<Service_pack, UUID> {
}
