package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaspring.hospitalmanagementsystem.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientControllers {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public Page<Patient> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "2") int size){
        System.out.println("fetching patients lists.");
        return  patientService.getAllPatients(page, size);
    }
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        System.out.println("creating patient.");
        return patientService.createPatient(patient);
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        System.out.println("Fetching patients by Id");
        return patientService.getPatientById(id);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        System.out.println("Patient deleted" + id);
        patientService.deletePatient(id);

    }
    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        System.out.println("Patient updated");
        patientService.updatePatient(id, patient);
    }
}
