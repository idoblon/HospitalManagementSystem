package com.javaspring.hospitalmanagementsystem.repository;

import com.javaspring.hospitalmanagementsystem.entity.Appointment;
import com.javaspring.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
