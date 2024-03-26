package com.trg.critter_chronologer.payload.dto;

import java.time.LocalDate;
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
public class EmployeeRequestDTO {
    @Size(min = 1)
    private Set<EmployeeSkill> skills;

    @NotNull
    private LocalDate date;
}
