package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Patient;
import com.javaspring.hospitalmanagementsystem.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;



@Service
public class PatientService {

    public static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size){
        try{
            System.out.println("Into service layer");
            Pageable pageable = PageRequest.of(page, size);
            return patientRepository.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching all patients: {}",e.getMessage());
            return null;
        }
    }

    public Patient getPatientById(Long id){
        try{
           Optional<Patient> patient = patientRepository.findById(id);
           return patient.orElse(null);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching  patients with Id {}: {}", id, e.getMessage());
            return null;
        }
    }

    public Patient createPatient(Patient patient){
        try{
            patientRepository.save(patient);
            return patient;
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while creating a new patient: {}",e.getMessage());
            return null;
        }
    }

    public void deletePatient(Long id){
        try{
            logger.info("Deleting patient with id: {}", id);
            patientRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while deleting the patient:{}", e.getMessage());
        }
    }

    public Patient updatePatient(Long id, Patient updatedPatient){
        try{
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if(existingPatient.isPresent()){
                Patient p = existingPatient.get();
                p.setName(updatedPatient.getName());
                p.setAge(updatedPatient.getAge());
                p.setGender(updatedPatient.getGender());
                patientRepository.save(p);

                return updatedPatient;
            }else{
                logger.error("Patient with ID {} not found", id);
                return null;
            }
        }catch(Exception e){
            System.out.println("Error message" + e.getMessage());
            logger.error("An error occurred while updating the patient:{}", e.getMessage(), e);
            return null;
        }
    }

}
