/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wien.patient_service_pdp3.JUnitTest;

/**
 *
 * @author Akram
 */
import com.wien.patient_service_pdp3.controller.PatientController;
import com.wien.patient_service_pdp3.model.Patient;
import com.wien.patient_service_pdp3.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    private Patient patient;

    @BeforeEach
    public void setup() {
        patient = new Patient();
        patient.setFirstName("Washim");
        patient.setLastName("Akram");
        patient.setDateOfBirth("1990-01-01");
        patient.setContactNumber("1234567890");
        patient.setEmailAddress("washim.akram@example.com");
        patient.setGender("Male");
    }

    @Test
    public void testAddPatient() throws Exception {
        when(patientService.addPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
//                .andExpect(status().isCreated())
            .andExpect(jsonPath("$.patient.firstName").value("Washim"));

    }

    @Test
    public void testGetAllPatients() throws Exception {
        List<Patient> patients = Collections.singletonList(patient);
        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients[0].firstName").value("Washim"));

    }

    @Test
    public void testGetPatientById() throws Exception {
        when(patientService.getPatientById(any(String.class))).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/patientinfobyid/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patient.firstName").value("Washim"));

    }

    @Test
    public void testUpdatePatient() throws Exception {
        when(patientService.updatePatient(any(String.class), any(Patient.class))).thenReturn(patient);

        mockMvc.perform(put("/api/patients/update/{id}", "66547d59e1b8332956c97837")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patient.firstName").value("Washim"));
    }

    @Test
public void testDeletePatient() throws Exception {
    when(patientService.deletePatient("1")).thenReturn(true); // Mock return true

    mockMvc.perform(delete("/api/patients/delete/{id}", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Patient deleted successfully"));

}

}