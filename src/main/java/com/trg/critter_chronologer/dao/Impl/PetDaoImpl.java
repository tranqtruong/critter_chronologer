package com.trg.critter_chronologer.dao.Impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.trg.critter_chronologer.dao.CustomerDao;
import com.trg.critter_chronologer.dao.PetDao;
import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.exception.UserException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PetDaoImpl implements PetDao {
    @PersistenceContext
    private EntityManager entityManager;

    private CustomerDao customerDao;

    @Autowired
    public PetDaoImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Optional<Pet> findById(Long petId) {
        return Optional.ofNullable(entityManager.find(Pet.class, petId));
    }

    @Override
    public Pet save(Pet pet) {
        if(isExists(pet.getId())) {
            return entityManager.merge(pet);
        } 

        entityManager.persist(pet);
        return pet;
    }

    @Override
    public Boolean isExists(Long petId) {
        return petId != null && findById(petId).isPresent();
    }

    @Override
    public Stream<Pet> findAll() {
        return entityManager.createQuery("select p from Pet p", Pet.class).getResultList().stream();
    }

    @Override
    public Stream<Pet> findAllByOwnerId(Long ownerId) {
        return customerDao.findById(ownerId).map(customer -> customer.getPets().stream()).orElseThrow(
            () -> new UserException("Customer not found for id: " + ownerId, HttpStatus.NOT_FOUND)
        );
    }
    
}
