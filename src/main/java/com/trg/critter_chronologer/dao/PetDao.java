package com.trg.critter_chronologer.dao;

import java.util.Optional;
import java.util.stream.Stream;

import com.trg.critter_chronologer.entity.Pet;

public interface PetDao {
    Optional<Pet> findById(Long petId);
    Pet save(Pet pet);
    Boolean isExists(Long petId);
    Stream<Pet> findAll();
    Stream<Pet> findAllByOwnerId(Long ownerId);
}
