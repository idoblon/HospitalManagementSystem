package com.javaspring.hospitalmanagementsystem.repository;

import com.javaspring.hospitalmanagementsystem.entity.Doctor;
import com.javaspring.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
