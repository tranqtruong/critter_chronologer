package com.trg.critter_chronologer.dao;

import java.util.Optional;
import java.util.stream.Stream;

import com.trg.critter_chronologer.entity.Customer;

public interface CustomerDao {
    Customer save(Customer customer);
    Boolean isExists(Long customerId);
    Optional<Customer> findById(Long customerId);
    Stream<Customer> findAll();
    Optional<Customer> findByPetId(long petId);
    // void deleteById(Long customerId);
    // void update(Customer customer);
}
