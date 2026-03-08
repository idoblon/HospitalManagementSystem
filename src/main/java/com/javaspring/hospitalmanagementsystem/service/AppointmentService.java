package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Appointment;
import com.javaspring.hospitalmanagementsystem.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;


@Service
public class AppointmentService {

    public final static Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Page<Appointment> getAllAppointment(int page, int size){
        try{
            System.out.println("Appointment Service layer.");
            Pageable pageable = PageRequest.of(page, size);
            return appointmentRepository.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching the appointment: {}", e.getMessage());
            return null;
        }
    }
    public Appointment getAppointmentById(Long id){
        try{
            Optional<Appointment> appointment = appointmentRepository.findById(id);
            return appointment.orElse(null);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while getting the appointment by Id {}:{}",id, e.getMessage());
            return null;
        }
    }
    public Appointment createAppointment(Appointment appointment){
        try{
            appointmentRepository.save(appointment);
            return appointment;
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while creating a new appointment:{}", e.getMessage());
            return null;
        }
    }
    public void deleteAppointment(Long id){
        try{
            logger.info("Deleting appointment with id: {}", id);
            appointmentRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while deleteing the appointment:{}", e.getMessage());
        }
    }
    public Appointment updateAppointment(Long id, Appointment updatedAppointment){
        try{
            Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
            if(existingAppointment.isPresent()){
                Appointment a = existingAppointment.get();
                a.setDoctorId(updatedAppointment.getDoctorId());
                a.setPatientId(updatedAppointment.getPatientId());
                a.setDate(updatedAppointment.getDate());
                return appointmentRepository.save(a);
            }else{
                logger.error("Appointment with ID {} is not found.", id);
                return null;
            }

        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while updating the appointment:{}", e.getMessage());
            return null;
        }
    }

}
