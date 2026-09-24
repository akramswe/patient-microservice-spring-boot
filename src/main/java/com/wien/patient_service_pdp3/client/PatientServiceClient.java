/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wien.patient_service_pdp3.client;

/**
 *
 * @author Akram
 */
import com.wien.patient_service_pdp3.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/api/patients";

    public List<Patient> getAllPatients() {
    try {
        Patient[] patients = restTemplate.getForObject(BASE_URL, Patient[].class);
        return patients != null ? Arrays.asList(patients) : Collections.emptyList();
    } catch (Exception e) {
        System.err.println("Error fetching patients: " + e.getMessage());
        return Collections.emptyList();
    }
}

public Patient getPatientById(String id) {
    if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("Patient ID must not be null or empty");
    }
    try {
        return restTemplate.getForObject(BASE_URL + "/patientinfobyid/" + id, Patient.class);
    } catch (Exception e) {
        System.err.println("Error fetching patient with ID " + id + ": " + e.getMessage());
        return null;
    }
}

public Patient addPatient(Patient patient) {
    if (patient == null) {
        throw new IllegalArgumentException("Patient must not be null");
    }
    try {
        return restTemplate.postForObject(BASE_URL + "/add", patient, Patient.class);
    } catch (Exception e) {
        System.err.println("Error adding patient: " + e.getMessage());
        return null;
    }
}

public void updatePatient(String id, Patient patient) {
    if (id == null || id.trim().isEmpty() || patient == null) {
        throw new IllegalArgumentException("Patient ID and data must not be null or empty");
    }
    try {
        restTemplate.put(BASE_URL + "/update/" + id, patient);
    } catch (Exception e) {
        System.err.println("Error updating patient with ID " + id + ": " + e.getMessage());
    }
}

public void deletePatient(String id) {
    if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("Patient ID must not be null or empty");
    }
    try {
        restTemplate.delete(BASE_URL + "/delete/" + id);
    } catch (Exception e) {
        System.err.println("Error deleting patient with ID " + id + ": " + e.getMessage());
    }
}

}