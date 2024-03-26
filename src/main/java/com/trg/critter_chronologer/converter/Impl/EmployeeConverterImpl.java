package com.trg.critter_chronologer.converter.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.trg.critter_chronologer.converter.EmployeeConverter;
import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.payload.dto.EmployeeDTO;

@Component
public class EmployeeConverterImpl implements EmployeeConverter {

    private ModelMapper modelMapper;

    public EmployeeConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public Employee convertToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
    
}
