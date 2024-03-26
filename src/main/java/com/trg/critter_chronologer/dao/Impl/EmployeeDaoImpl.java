package com.trg.critter_chronologer.dao.Impl;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.trg.critter_chronologer.dao.EmployeeDao;
import com.trg.critter_chronologer.entity.Employee;
import com.trg.critter_chronologer.entity.EmployeeSkill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee save(Employee employee) {
        if(isExists(employee.getId())){
            return entityManager.merge(employee);
        }

        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Boolean isExists(Long employeeId) {
        return employeeId != null && entityManager.find(Employee.class, employeeId) != null;
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        return Optional.ofNullable(entityManager.find(Employee.class, employeeId));
    }

    @Override
    public Stream<Employee> findAllByDaysAvailableAndSkills(Set<EmployeeSkill> skills, DayOfWeek daysAvailable) {
        String jpql = "SELECT DISTINCT e FROM Employee e JOIN e.skills s JOIN e.daysAvailable d WHERE s IN :skills AND d = :daysAvailable";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("skills", skills);
        query.setParameter("daysAvailable", daysAvailable);
        return query.getResultList().stream();
    }


}
