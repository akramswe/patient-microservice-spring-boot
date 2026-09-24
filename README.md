# Patient Microservice - Spring Boot

A RESTful Patient Microservice built with Spring Boot and MongoDB.

This project provides CRUD operations for managing patient data and includes
automated tests, Docker containerization, Docker Compose, and a GitLab CI/CD
pipeline.

## Features

- Get all patients
- Get a patient by ID
- Add a new patient
- Update an existing patient
- Delete a patient
- MongoDB database integration
- RESTful API
- JUnit tests
- Mockito-based controller tests
- Endpoint/integration tests
- Dockerized application
- Docker Compose setup
- GitLab CI/CD pipeline

## Technologies Used

- Java
- Spring Boot
- Spring Data MongoDB
- Maven
- MongoDB
- JUnit
- Mockito
- Docker
- Docker Compose
- GitLab CI/CD

## Patient Entity

The Patient entity contains the following fields:

- First Name
- Last Name
- Date of Birth
- Contact Number
- Email Address
- Gender

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/patients` | Get all patients |
| GET | `/patients/{id}` | Get a patient by ID |
| POST | `/patients` | Add a new patient |
| PUT | `/patients/{id}` | Update an existing patient |
| DELETE | `/patients/{id}` | Delete a patient |

## Running the Application

### Run with Maven

Linux/macOS:

```bash
./mvnw spring-boot:run
