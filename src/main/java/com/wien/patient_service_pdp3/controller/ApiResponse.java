/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wien.patient_service_pdp3.controller;

import com.wien.patient_service_pdp3.model.Patient;
import java.util.List;

/**
 *
 * @author Akram
 */
public class ApiResponse {
     private String message;
    private Patient patient;
    private List<Patient> patients;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, Patient patient) {
        this.message = message;
        this.patient = patient;
    }

    public ApiResponse(String message, List<Patient> patients) {
        this.message = message;
        this.patients = patients;
    }

    public String getMessage() {
        return message;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}