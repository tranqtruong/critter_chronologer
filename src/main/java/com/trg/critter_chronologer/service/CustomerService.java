package com.trg.critter_chronologer.service;

import java.util.List;

import com.trg.critter_chronologer.payload.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getOwnerByPet(long petId);
}
