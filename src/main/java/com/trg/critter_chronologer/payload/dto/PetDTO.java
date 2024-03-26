package com.trg.critter_chronologer.payload.dto;

import java.time.LocalDate;

import com.trg.critter_chronologer.entity.PetType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PetDTO {
    private Long id;

    @NotNull
    private PetType type;

    @NotBlank
    private String name;

    @NotNull
    private Long ownerId;
    private LocalDate birthDate;
    private String notes;
}
