package com.trg.critter_chronologer.service;

import java.util.List;

import com.trg.critter_chronologer.payload.dto.PetDTO;

public interface PetService {
    PetDTO save(PetDTO petDTO);
    List<PetDTO> getAllPets();
    List<PetDTO> getPetsByOwner(long ownerId);
    PetDTO findById(long petId);
}
