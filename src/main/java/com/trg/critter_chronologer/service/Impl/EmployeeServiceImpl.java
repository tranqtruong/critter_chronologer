package com.trg.critter_chronologer.service.Impl;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trg.critter_chronologer.converter.EmployeeConverter;
import com.trg.critter_chronologer.dao.EmployeeDao;
import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.exception.UserException;
import com.trg.critter_chronologer.payload.dto.EmployeeDTO;
import com.trg.critter_chronologer.payload.dto.EmployeeRequestDTO;
import com.trg.critter_chronologer.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private EmployeeConverter employeeConverter;

    public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeConverter employeeConverter) {
        this.employeeDao = employeeDao;
        this.employeeConverter = employeeConverter;
    }

    @Override
    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        if(employeeDao.isExists(employeeDTO.getId())) {
            throw new UserException("Employee already exists for id: " + employeeDTO.getId(), HttpStatus.CONFLICT);
        }
        Employee employee = employeeConverter.convertToEmployee(employeeDTO);
        employee = employeeDao.save(employee);
        return employeeConverter.convertToEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO addSchedule(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeDao.findById(employeeId)
            .map(employeeToBeUpdated -> {
                employeeToBeUpdated.setDaysAvailable(daysAvailable);
                return employeeDao.save(employeeToBeUpdated);
            })
            .orElseThrow(() -> new UserException("Employee not found with id: " + employeeId, HttpStatus.NOT_FOUND));
        return employeeConverter.convertToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAvailability(EmployeeRequestDTO employeeDTO) {
        return employeeDao.findAllByDaysAvailableAndSkills(
            employeeDTO.getSkills(),
            employeeDTO.getDate().getDayOfWeek()
        ).map(employeeConverter::convertToEmployeeDTO).toList();
    }

    @Override
    public EmployeeDTO findById(Long employeeId) {
        return employeeDao.findById(employeeId)
            .map(employeeConverter::convertToEmployeeDTO)
            .orElseThrow(() -> new UserException("Employee not found for id: " + employeeId, HttpStatus.NOT_FOUND));
    }
    
}
