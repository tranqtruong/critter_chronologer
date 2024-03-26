package com.trg.critter_chronologer.payload.dto;

import java.time.DayOfWeek;
import java.util.Set;

import com.trg.critter_chronologer.entity.EmployeeSkill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EmployeeDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Set<EmployeeSkill> skills;

    private Set<DayOfWeek> daysAvailable;
}
