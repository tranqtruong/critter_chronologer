package com.trg.critter_chronologer.payload.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.trg.critter_chronologer.entity.EmployeeSkill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ScheduleDTO {
    private Long id;

    @Size(min = 1, message = "least one employee is required")
    private List<Long> employeeIds;

    @Size(min = 1, message = "least one pet is required")
    private List<Long> petIds;

    @NotNull
    private LocalDate date;

    @Size(min = 1, message = "least one activity is required")
    private Set<EmployeeSkill> activities;
}
