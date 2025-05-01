# 🐾 Critter Chronologer

A scheduling and management backend system for a pet service business, allowing users to register pets, assign employees, and schedule appointments. Developed as a backend practice project using Spring Boot and pure JPA with EntityManager.

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot**
- **JPA**
- **H2 (in-memory database)**
- **Lombok**

---

## 🚀 Features

### Customer & Pet Management
- `POST /customer` – Create customer  
- `POST /pet` – Register a pet  
- `GET /customer` – Get all customers  
- `GET /pet` – Get all pets  
- `GET /pet/owner/{ownerId}` – Get pets by owner  
- `GET /pet/{petId}` – Find pet by ID  
- `GET /pet/owner/{petId}` – Get owner by pet  

### Employee Scheduling
- `POST /employee` – Create employee  
- `PUT /employee/schedule` – Set employee schedule  
- `GET /employee/availability` – Check availability  
- `GET /employee/{id}` – Find employee by ID  

### Schedule Management
- `POST /schedule` – Create schedule  
- `GET /schedule/pet/{petId}` – Find schedule by pet  
- `GET /schedule/employee/{employeeId}` – Find schedule by employee  
- `GET /schedule/owner/{ownerId}` – Find schedule by owner  
- `GET /schedule` – Get all schedules  

---

## 🗃️ Database

- **H2 in-memory database** is used for development and testing.  
- JPA is used via manual `EntityManager` instead of Spring Data JPA.  
- Core entities:
  - `Customer`
  - `Pet`
  - `Employee`
  - `Schedule`

---

## 📦 How to Run

```bash
# Clone project
git clone https://github.com/tranqtruong/critter_chronologer.git
cd critter_chronologer

# Run with Maven Wrapper
./mvnw spring-boot:run
```

- The H2 Console is available at `http://localhost:8080/h2-console`  
- Default JDBC URL: `jdbc:h2:mem:testdb`

---

## 📌 Learning Objectives

This project was built to practice:
- Building RESTful APIs with Spring Boot (Java 17)
- Manual JPA persistence using `EntityManager`
- Designing relational entities (OneToMany, ManyToMany)
- Implementing business logic around schedules and availability
- Using H2 DB for lightweight development/testing

---
