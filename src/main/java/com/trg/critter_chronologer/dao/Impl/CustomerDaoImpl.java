package com.trg.critter_chronologer.dao.Impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.trg.critter_chronologer.dao.CustomerDao;
import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.exception.UserException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer save(Customer customer) {
        if(isExists(customer.getId())){
            return entityManager.merge(customer);
        }
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Boolean isExists(Long customerId) {
        return customerId != null && entityManager.find(Customer.class, customerId) != null;
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return Optional.ofNullable(entityManager.find(Customer.class, customerId));
    }

    @Override
    public Stream<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList().stream();
    }

    @Override
    public Optional<Customer> findByPetId(long petId) {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c INNER JOIN c.pets p WHERE p.id = :petId", Customer.class);
            query.setParameter("petId", petId);
            return Optional.ofNullable(query.getSingleResult());
        } catch(NoResultException e) {
            //add log here
        } catch(NonUniqueResultException e){
            //add log here
        }
        return Optional.empty();
    }
    
}
