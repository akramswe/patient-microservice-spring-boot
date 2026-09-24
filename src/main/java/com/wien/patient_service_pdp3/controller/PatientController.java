/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wien.patient_service_pdp3.controller;

/**
 *
 * @author Akram
 */
import com.wien.patient_service_pdp3.model.Patient;
import com.wien.patient_service_pdp3.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

   @GetMapping
    public ResponseEntity<ApiResponse> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(new ApiResponse("All patients info retrieved", patients));
    }


   @GetMapping("/patientinfobyid/{id}")
public ResponseEntity<ApiResponse> getPatientById(@PathVariable String id) {
    return patientService.getPatientById(id)
            .map(patient -> ResponseEntity.ok(new ApiResponse("Patient found by id: "+ id, patient)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}


    @PostMapping("/add")
public ResponseEntity<ApiResponse> addPatient(@RequestBody Patient patient) {
    Patient createdPatient = patientService.addPatient(patient);
    if (createdPatient != null) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Patient info added successfully", createdPatient));
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

//     @PostMapping
//   public List<Patient> addPatient(@RequestBody List<Patient> patient) {
//        return patientService.addPatient(patient);
//    }

@PutMapping("/update/{id}")
public ResponseEntity<ApiResponse> updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
    Patient updatedPatient = patientService.updatePatient(id, patientDetails);
    if (updatedPatient != null) {
        return ResponseEntity.ok(new ApiResponse("Patient updated successfully", updatedPatient));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


   @DeleteMapping("/delete/{id}")
public ResponseEntity<ApiResponse> deletePatient(@PathVariable String id) {
    boolean deleted = patientService.deletePatient(id);
    if (deleted) {
        return ResponseEntity.ok(new ApiResponse("Patient deleted successfully"));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Patient not found for deletion"));
    }
}


}