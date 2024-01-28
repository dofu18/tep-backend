package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.DTO.TimeshareDTO;
import com.main.timeshareexchangeplatform_backend.converter.TimeshareConverter;
import com.main.timeshareexchangeplatform_backend.repository.MyTimeShareRepository;
import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.service.TimeShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class TimeShareServiceImp implements TimeShareService {
        @Autowired
        private MyTimeShareRepository myTimeShareRepository;
        @Autowired
    TimeshareConverter timeshareConverter;
        public List<TimeshareDTO> showListTimeShare(){
             List<Timeshare> timeshares = myTimeShareRepository.showListTimeShare();
            return timeshareConverter.convertToAccountPlaylistDTOList(timeshares);

        }
    }
