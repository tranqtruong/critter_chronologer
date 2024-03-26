package com.trg.critter_chronologer.converter;

import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.payload.dto.EmployeeDTO;

/**
 * EmployeeConverter
 */
public interface EmployeeConverter {
    EmployeeDTO convertToEmployeeDTO(Employee employee);
    Employee convertToEmployee(EmployeeDTO employeeDTO);
}
