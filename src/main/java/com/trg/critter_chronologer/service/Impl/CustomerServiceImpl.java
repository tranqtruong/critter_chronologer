package com.trg.critter_chronologer.service.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trg.critter_chronologer.converter.CustomerConverter;
import com.trg.critter_chronologer.dao.CustomerDao;
import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.exception.UserException;
import com.trg.critter_chronologer.payload.dto.CustomerDTO;
import com.trg.critter_chronologer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private CustomerConverter customerConverter;

    public CustomerServiceImpl(CustomerDao customerDao, CustomerConverter customerConverter) {
        this.customerDao = customerDao;
        this.customerConverter = customerConverter;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        if(customerDao.isExists(customerDTO.getId())) {
            throw new UserException("Customer already exists for id: " + customerDTO.getId(), HttpStatus.CONFLICT);
        }
        Customer customer = customerConverter.convertToCustomer(customerDTO);
        customer = customerDao.save(customer);
        return customerConverter.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDao.findAll().map(customerConverter::convertToCustomerDTO).toList();
    }

    @Override
    public CustomerDTO getOwnerByPet(long petId) {
        return customerDao.findByPetId(petId).map(customerConverter::convertToCustomerDTO).orElseThrow(() ->
            new UserException("Customer not found for pet id: " + petId, HttpStatus.NOT_FOUND)
        );
    }
    
}
