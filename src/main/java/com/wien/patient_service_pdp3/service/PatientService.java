/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wien.patient_service_pdp3.service;

/**
 *
 * @author Akram
 */
import com.wien.patient_service_pdp3.model.Patient;
import com.wien.patient_service_pdp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

     public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    

//    public List<Patient> addPatient(List<Patient> patient) {
//        return patientRepository.saveAll(patient);
//    }

    public Patient updatePatient(String id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setLastName(patientDetails.getLastName());
                    patient.setDateOfBirth(patientDetails.getDateOfBirth());
                    patient.setContactNumber(patientDetails.getContactNumber());
                    patient.setEmailAddress(patientDetails.getEmailAddress());
                    patient.setGender(patientDetails.getGender());
                    return patientRepository.save(patient);
                }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

        public boolean deletePatient(String id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
