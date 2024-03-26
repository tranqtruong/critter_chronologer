package com.trg.critter_chronologer.service.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trg.critter_chronologer.converter.ScheduleConverter;
import com.trg.critter_chronologer.dao.ScheduleDao;
import com.trg.critter_chronologer.entity.Schedule;
import com.trg.critter_chronologer.exception.ScheduleException;
import com.trg.critter_chronologer.payload.dto.ScheduleDTO;
import com.trg.critter_chronologer.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleDao scheduleDao;
    private ScheduleConverter scheduleConverter;

    public ScheduleServiceImpl(ScheduleDao scheduleDao, ScheduleConverter scheduleConverter) {
        this.scheduleDao = scheduleDao;
        this.scheduleConverter = scheduleConverter;
    }

    @Override
    @Transactional
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        if(scheduleDao.isExists(scheduleDTO.getId())) {
            throw new ScheduleException("Schedule already exists for id: " + scheduleDTO.getId(), HttpStatus.CONFLICT);
        }
        return scheduleConverter.convertToDTO(scheduleDao.save(scheduleConverter.convertToEntity(scheduleDTO)));
    }

    @Override
    public List<ScheduleDTO> getSchedulesByPet(Long petId) {
        return scheduleDao.findAllByPetId(petId).map(scheduleConverter::convertToDTO).toList();
    }

    @Override
    public List<ScheduleDTO> getSchedulesByEmployee(Long employeeId) {
        return scheduleDao.findAllByEmployeeId(employeeId).map(scheduleConverter::convertToDTO).toList();
    }

    @Override
    public List<ScheduleDTO> getSchedulesByCustomer(Long customerId) {
        return scheduleDao.findAllByCustomerId(customerId).map(scheduleConverter::convertToDTO).toList();
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleDao.findAll().map(scheduleConverter::convertToDTO).toList();
    }
    
}
