package com.trg.critter_chronologer.dao;

import java.util.stream.Stream;

import com.trg.critter_chronologer.entity.Schedule;

public interface ScheduleDao {
    Schedule save(Schedule schedule);
    Boolean isExists(Long scheduleId);
    Stream<Schedule> findAll();
    Stream<Schedule> findAllByEmployeeId(Long employeeId);
    Stream<Schedule> findAllByPetId(Long petId);
    Stream<Schedule> findAllByCustomerId(Long customerId);
    
}
