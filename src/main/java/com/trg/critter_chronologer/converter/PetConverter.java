package com.trg.critter_chronologer.converter;

import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.payload.dto.PetDTO;

public interface PetConverter {
    public PetDTO convertToPetDTO(Pet pet);
    public Pet convertToPet(PetDTO petDTO);
}
