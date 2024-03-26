package com.trg.critter_chronologer.dao.Impl;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.trg.critter_chronologer.dao.ScheduleDao;
import com.trg.critter_chronologer.entity.Customer;
import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.entity.Pet;
import com.trg.critter_chronologer.entity.Schedule;
import com.trg.critter_chronologer.exception.UserException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Schedule save(Schedule schedule) {
        if(isExists(schedule.getId())) {
            return entityManager.merge(schedule);
        }

        entityManager.persist(schedule);
        return schedule;
    }

    @Override
    public Boolean isExists(Long scheduleId) {
        return scheduleId != null && entityManager.find(Schedule.class, scheduleId) != null;
    }

    @Override
    public Stream<Schedule> findAll() {
        return entityManager.createQuery("select s from Schedule s", Schedule.class).getResultStream();
    }

    @Override
    public Stream<Schedule> findAllByEmployeeId(Long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if(employee == null) {
            throw new UserException("Employee not found for id: " + employeeId, HttpStatus.NOT_FOUND);
        }
        return employee.getSchedules().stream();
    }

    @Override
    public Stream<Schedule> findAllByPetId(Long petId) {
        Pet pet = entityManager.find(Pet.class, petId);
        if(pet == null) {
            throw new UserException("Pet not found for id: " + petId, HttpStatus.NOT_FOUND);
        }
        return pet.getSchedules().stream();

    }

    @Override
    public Stream<Schedule> findAllByCustomerId(Long customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if(customer == null) {
            throw new UserException("Customer not found for id: " + customerId, HttpStatus.NOT_FOUND);
        }

        String jpql = "select distinct s from Schedule s inner join s.pets p inner join p.owner c where c.id = :customerId";
        TypedQuery<Schedule> query = entityManager.createQuery(jpql, Schedule.class);
        query.setParameter("customerId", customerId);
        return query.getResultStream();
    }
    
}
