package com.trg.critter_chronologer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.critter_chronologer.payload.dto.ScheduleDTO;
import com.trg.critter_chronologer.service.ScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleDTO));
    }

    @GetMapping("/pets/{petId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByPet(@PathVariable("petId") Long petId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByPet(petId));
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByEmployee(employeeId));
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByCustomer(customerId));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }
}
