package com.trg.critter_chronologer.service;

import java.util.List;

import com.trg.critter_chronologer.payload.dto.ScheduleDTO;

public interface ScheduleService {
    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> getSchedulesByPet(Long petId);
    List<ScheduleDTO> getSchedulesByEmployee(Long employeeId);
    List<ScheduleDTO> getSchedulesByCustomer(Long customerId);
    List<ScheduleDTO> getAllSchedules();
}
