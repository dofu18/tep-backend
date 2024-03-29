package com.main.timeshareexchangeplatform_backend.converter;

import com.main.timeshareexchangeplatform_backend.dto.BookingResponse;
import com.main.timeshareexchangeplatform_backend.dto.DestinationModel;
import com.main.timeshareexchangeplatform_backend.dto.TransactionDTO;
import com.main.timeshareexchangeplatform_backend.dto.TransactionResponse;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.entity.Destination;
import com.main.timeshareexchangeplatform_backend.entity.Transaction_history;
import com.main.timeshareexchangeplatform_backend.repository.ServicePackRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    ServicePackRepository servicePackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServicePackConverter servicePackConverter;
    @Autowired
    UserConverter userConverter;
    public Transaction_history toEntity(TransactionDTO dto) {

        Transaction_history entity = new Transaction_history();

        entity.setTransaction_id(dto.getTransaction_id());
        entity.setTransaction_fee(dto.getTransaction_fee());
        entity.setTransaction_time(dto.getTransaction_time());
        entity.setServicePack(servicePackRepository.getReferenceById(dto.getService_id()));
        entity.setUser(userRepository.getReferenceById(dto.getUser_id()));
        entity.setTransactionCode(dto.getTransaction_code());
        entity.setExpireDate(dto.getExpireDate());

        return entity;
    }

    public TransactionDTO toDTO(Transaction_history entity) {

        TransactionDTO dto = new TransactionDTO();

        dto.setTransaction_id(entity.getTransaction_id());
        dto.setTransaction_fee(entity.getTransaction_fee());
        dto.setTransaction_time(entity.getTransaction_time());
        dto.setService_id(entity.getServicePack().getService_id());
        dto.setUser_id(entity.getUser().getUser_id());
        dto.setTransaction_code(entity.getTransactionCode());
        dto.setExpireDate(entity.getExpireDate());

        return dto;
    }

    public TransactionResponse toRespone(Transaction_history entity) {
        TransactionResponse dto = new TransactionResponse();

        dto.setTransaction_id(entity.getTransaction_id());
        dto.setTransaction_fee(entity.getTransaction_fee());
        dto.setTransaction_time(entity.getTransaction_time());
        dto.setService_id(servicePackConverter.toDTO(entity.getServicePack()));
        dto.setUser_id(userConverter.toDTO(entity.getUser()));
        dto.setTransaction_code(entity.getTransactionCode());
        dto.setExpireDate(entity.getExpireDate());

        return dto;
    }

    public Transaction_history toResEntity(TransactionResponse dto) {
        Transaction_history entity = new Transaction_history();

        entity.setTransaction_id(dto.getTransaction_id());
        entity.setTransaction_fee(dto.getTransaction_fee());
        entity.setTransaction_time(dto.getTransaction_time());
        entity.setServicePack(servicePackConverter.toEntity(dto.getService_id()));
        entity.setUser(userConverter.toEntity(dto.getUser_id()));
        entity.setTransactionCode(dto.getTransaction_code());
        entity.setExpireDate(dto.getExpireDate());

        return entity;
    }

}
