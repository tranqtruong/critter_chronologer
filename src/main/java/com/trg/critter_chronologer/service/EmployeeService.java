package com.trg.critter_chronologer.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import com.trg.critter_chronologer.payload.dto.EmployeeDTO;
import com.trg.critter_chronologer.payload.dto.EmployeeRequestDTO;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO addSchedule(Long employeeId, Set<DayOfWeek> daysAvailable);
    List<EmployeeDTO> getAvailability(EmployeeRequestDTO employeeDTO);
    EmployeeDTO findById(Long employeeId);
}
