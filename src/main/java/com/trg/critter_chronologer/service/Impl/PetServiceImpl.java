package com.trg.critter_chronologer.service.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trg.critter_chronologer.converter.PetConverter;
import com.trg.critter_chronologer.dao.PetDao;
import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.exception.PetException;
import com.trg.critter_chronologer.payload.dto.PetDTO;
import com.trg.critter_chronologer.service.PetService;

@Service
public class PetServiceImpl implements PetService {

    private PetConverter petConverter;
    private PetDao petDao;

    public PetServiceImpl(PetConverter petConverter, PetDao petDao) {
        this.petConverter = petConverter;
        this.petDao = petDao;
    }

    @Override
    @Transactional
    public PetDTO save(PetDTO petDTO) {
        if(petDao.isExists(petDTO.getId())) {
            throw new PetException("Pet already exists for id: " + petDTO.getId(), HttpStatus.CONFLICT);
        }
        Pet pet = petConverter.convertToPet(petDTO);
        return petConverter.convertToPetDTO(petDao.save(pet));
    }

    @Override
    public List<PetDTO> getAllPets() {
        return petDao.findAll().map(petConverter::convertToPetDTO).toList();
    }

    @Override
    public List<PetDTO> getPetsByOwner(long ownerId) {
        return petDao.findAllByOwnerId(ownerId).map(petConverter::convertToPetDTO).toList();
    }

    @Override
    public PetDTO findById(long petId) {
        return petDao.findById(petId)
            .map(petConverter::convertToPetDTO)
            .orElseThrow(() -> new PetException("Pet not found for id: " + petId, HttpStatus.NOT_FOUND));
    }
    
}
