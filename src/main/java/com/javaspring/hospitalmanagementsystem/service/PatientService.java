package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Patient;
import com.javaspring.hospitalmanagementsystem.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;



@Service
public class PatientService {

    public static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Page<Patient> getAllPatients(int page, int size){
        try{
            logger.info("Fetching patient list - page: {}, size: {}", page, size);
            Pageable pageable = PageRequest.of(page, size);
            return patientRepository.findAll(pageable);
        }catch(Exception e){
            logger.error("An error occurred while fetching all patients: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch patients", e);
        }
    }

    public Patient getPatientById(Long id){
        try{
           return patientRepository.findById(id).orElse(null);
        }catch(Exception e){
            logger.error("An error occurred while fetching patients with Id {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch patient by id", e);
        }
    }

    public Patient createPatient(Patient patient){
        try{
            logger.info("Creating a new patient.");
            return patientRepository.save(patient);
        }catch(Exception e){
            logger.error("An error occurred while creating a new patient: {}",e.getMessage());
            throw new RuntimeException("Failed to create patient", e);
        }
    }

    public void deletePatient(Long id){
        try{
            logger.info("Deleting patient with id: {}", id);
            patientRepository.deleteById(id);
        }catch(Exception e){
            logger.error("An error occurred while deleting the patient:{}", e.getMessage());
            throw new RuntimeException("Failed to delete patient", e);
        }
    }

    public Patient updatePatient(Long id, Patient updatedPatient){
        try{
            return patientRepository.findById(id)
                    .map(p -> {
                        p.setName(updatedPatient.getName());
                        p.setAge(updatedPatient.getAge());
                        p.setGender(updatedPatient.getGender());
                        return patientRepository.save(p);
                    })
                    .orElseGet(() -> {
                        logger.error("Patient with ID {} not found", id);
                        return null;
                    });
        }catch(Exception e){
            logger.error("An error occurred while updating the patient:{}", e.getMessage(), e);
            throw new RuntimeException("Failed to update patient", e);
        }
    }

}
