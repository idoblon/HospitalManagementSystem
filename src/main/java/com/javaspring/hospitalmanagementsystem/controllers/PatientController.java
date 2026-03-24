package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Patient;
import com.javaspring.hospitalmanagementsystem.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "2") int size){
        logger.info("Fetching patients list.");
        return ResponseEntity.ok(patientService.getAllPatients(page, size));
    }
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        logger.info("Creating a new patient.");
        return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        logger.info("Fetching patient by id: {}", id);
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        logger.info("Deleting patient with id: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        logger.info("Updating patient with id: {}", id);
        patientService.updatePatient(id, patient);
        return ResponseEntity.ok().build();
    }
}
