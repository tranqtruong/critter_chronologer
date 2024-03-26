package com.trg.critter_chronologer.controller;

import java.time.DayOfWeek;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.critter_chronologer.payload.dto.CustomerDTO;
import com.trg.critter_chronologer.payload.dto.EmployeeDTO;
import com.trg.critter_chronologer.payload.dto.EmployeeRequestDTO;
import com.trg.critter_chronologer.service.CustomerService;
import com.trg.critter_chronologer.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private EmployeeService employeeService;
    private CustomerService customerService;

    public UserController(EmployeeService employeeService, CustomerService customerService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<EmployeeDTO>(employeeService.save(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> addEmployeeSchedule(
        @PathVariable("id") Long employeeId,
        @RequestBody @NotNull @Size(min = 1, message = "Minimum is 1") Set<DayOfWeek> daysAvailable
    ) {
        return ResponseEntity.ok(employeeService.addSchedule(employeeId, daysAvailable));
    }

    @GetMapping("/employees/availability")
    public ResponseEntity<?> getEmployeeAvailability(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.getAvailability(employeeRequestDTO));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.created(null).body(customerService.save(customerDTO));
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/customers/pets/{petId}")
    public ResponseEntity<CustomerDTO> getOwnerByPet(@PathVariable("petId") Long petId) {
        return ResponseEntity.ok(customerService.getOwnerByPet(petId));
    }

}
