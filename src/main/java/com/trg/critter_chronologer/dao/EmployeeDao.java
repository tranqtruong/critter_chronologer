package com.trg.critter_chronologer.dao;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.entity.EmployeeSkill;

public interface EmployeeDao {
    Boolean isExists(Long employeeId);
    Employee save(Employee employee);
    Optional<Employee> findById(Long employeeId);
    Stream<Employee> findAllByDaysAvailableAndSkills(Set<EmployeeSkill> skills, DayOfWeek daysAvailable);
    
    // Employee setSchedule(Employee employee, Set<DayOfWeek> daysAvailable);
    // List<Employee> getAvailability(List<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}
