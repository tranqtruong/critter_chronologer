package com.trg.critter_chronologer.converter.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.trg.critter_chronologer.converter.ScheduleConverter;
import com.trg.critter_chronologer.dao.EmployeeDao;
import com.trg.critter_chronologer.dao.PetDao;
import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.entity.Schedule;
import com.trg.critter_chronologer.exception.PetException;
import com.trg.critter_chronologer.exception.UserException;
import com.trg.critter_chronologer.payload.dto.ScheduleDTO;

@Component
public class ScheduleConverterImpl implements ScheduleConverter {

    private ModelMapper modelMapper;
    private EmployeeDao employeeDao;
    private PetDao petDao;

    public ScheduleConverterImpl(ModelMapper modelMapper, EmployeeDao employeeDao, PetDao petDao) {
        this.modelMapper = modelMapper;
        this.employeeDao = employeeDao;
        this.petDao = petDao;
    }

    @Override
    public ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList()));

        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList()));
        
        return scheduleDTO;
    }

    @Override
    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);

        List<Employee> employees = scheduleDTO.getEmployeeIds().stream()
                .map(employeeId -> employeeDao
                        .findById(employeeId)
                        .orElseThrow(() -> new UserException(
                            "Employee not found for id: " + employeeId, 
                            HttpStatus.NOT_FOUND)
                        )
                )
                .collect(Collectors.toList());

        schedule.setEmployees(employees);

        List<Pet> pets = scheduleDTO.getPetIds().stream()
                .map(petId -> petDao.findById(petId).orElseThrow(() -> new PetException(
                        "Pet not found for id: " + petId, 
                        HttpStatus.NOT_FOUND
                ))).toList();

        schedule.setPets(pets);
        return schedule;
    }
    
}
