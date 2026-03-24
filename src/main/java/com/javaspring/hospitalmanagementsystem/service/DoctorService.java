package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Doctor;
import com.javaspring.hospitalmanagementsystem.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class DoctorService {

    public static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Page<Doctor> getAllDoctors(int page, int size){
        try{
            logger.info("Fetching all doctors - page: {}, size: {}", page, size);
            Pageable pageable = PageRequest.of(page, size);
            return doctorRepository.findAll(pageable);
        }catch(Exception e){
            logger.error("An error occurred while fetching all Doctors: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch doctors", e);
        }
    }
    public Doctor getDoctorById(Long id){
        try{
            return doctorRepository.findById(id).orElse(null);
        }catch(Exception e){
            logger.error("An error occurred while fetching by Id {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch doctor by id", e);
        }
    }
    public Doctor createDoctor(Doctor doctor){
        try{
            logger.info("Creating a new doctor: {}", doctor.getName());
            return doctorRepository.save(doctor);
        }catch(Exception e){
            logger.error("An error occurred while creating a new Doctor :{}", e.getMessage());
            throw new RuntimeException("Failed to create doctor", e);
        }
    }
    public void deleteDoctor(Long id){
        try{
            logger.info("Deleting doctor with id: {}", id);
            doctorRepository.deleteById(id);
        }catch(Exception e){
            logger.error("An error occurred while deleting the doctor:{}", e.getMessage());
            throw new RuntimeException("Failed to delete doctor", e);
        }
    }
    public Doctor updateDoctor(Long id, Doctor updatedDoctor){
        try{
            return doctorRepository.findById(id)
                    .map(d -> {
                        d.setName(updatedDoctor.getName());
                        d.setSpecialty(updatedDoctor.getSpecialty());
                        return doctorRepository.save(d);
                    })
                    .orElseGet(() -> {
                        logger.error("Doctor with ID {} not found", id);
                        return null;
                    });
        }catch(Exception e){
            logger.error("An error occurred while updating Doctor:{}", e.getMessage());
            throw new RuntimeException("Failed to update doctor", e);
        }
    }
}
