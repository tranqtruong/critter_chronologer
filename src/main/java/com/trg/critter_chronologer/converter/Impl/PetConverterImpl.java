package com.trg.critter_chronologer.converter.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.trg.critter_chronologer.converter.PetConverter;
import com.trg.critter_chronologer.dao.CustomerDao;
import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.exception.UserException;
import com.trg.critter_chronologer.payload.dto.PetDTO;

@Component
public class PetConverterImpl implements PetConverter {
    private ModelMapper modelMapper;
    private CustomerDao  customerDao;

    public PetConverterImpl(ModelMapper modelMapper, CustomerDao customerDao) {
        this.modelMapper = modelMapper;
        this.customerDao = customerDao;
    }   

    @Override
    public PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    @Override
    public Pet convertToPet(PetDTO petDTO) {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        Customer customer = customerDao.findById(petDTO.getOwnerId()).map(owner -> {
            return owner;
        }).orElseThrow(() -> 
            new UserException("Owner not found for id: " + petDTO.getOwnerId(), 
            HttpStatus.NOT_FOUND)
        );
        pet.setOwner(customer);
        return pet;
    }
    
}
