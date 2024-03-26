package com.trg.critter_chronologer.converter;

import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.payload.dto.CustomerDTO;

public interface CustomerConverter {
    CustomerDTO convertToCustomerDTO(Customer customer);
    Customer convertToCustomer(CustomerDTO customerDTO);
}
