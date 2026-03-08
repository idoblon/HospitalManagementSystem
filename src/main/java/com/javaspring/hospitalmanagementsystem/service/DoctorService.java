package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Doctor;
import com.javaspring.hospitalmanagementsystem.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class DoctorService {

    public static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Page<Doctor> getAllDoctors(int page, int size){
        try{
            System.out.println("Doctor service layer");
            Pageable pageable = PageRequest.of(page, size);
            return doctorRepository.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching all Doctors: {}", e.getMessage());
            return null;
        }
    }
    public Doctor getDoctorById(Long id){
        try{
            Optional<Doctor> doctor = doctorRepository.findById(id);
            return doctor.orElse(null);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching by Id {}: {}", id, e.getMessage());
            return null;
        }
    }
    public Doctor createDoctor(Doctor doctor){
        try{
            doctorRepository.save(doctor);
            return doctor;
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while creating a new Doctor :{}", e.getMessage());
            return null;
        }
    }
    public void deleteDoctor(Long id){
        try{
            logger.info("Deleting doctor with id: {}", id);
            doctorRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while deleting the doctor:{}", e.getMessage());
        }
    }
    public Doctor updateDoctor(Long id, Doctor updatedDoctor){
        try{
            Optional<Doctor> existingDoctor = doctorRepository.findById(id);
            if(existingDoctor.isPresent()){
                Doctor d = existingDoctor.get();
                d.setName(updatedDoctor.getName());
                d.setSpecialty(updatedDoctor.getSpecialty());
                return doctorRepository.save(d);
            }else{
                logger.error("Doctor with ID {} not found", id);
                return null;
            }
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while updating Doctor:{}", e.getMessage());
            return null;
        }
    }
}
