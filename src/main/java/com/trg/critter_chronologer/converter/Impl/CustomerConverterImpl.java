package com.trg.critter_chronologer.converter.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.trg.critter_chronologer.converter.CustomerConverter;
import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.payload.dto.CustomerDTO;

@Component
public class CustomerConverterImpl implements CustomerConverter {
    private ModelMapper modelMapper;

    public CustomerConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        List<Long> petIds = Optional.ofNullable(customer.getPets()).map(petList -> 
            petList.stream()
                    .map(pet -> pet.getId())
                    .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    @Override
    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
    
}
