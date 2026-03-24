package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Appointment;
import com.javaspring.hospitalmanagementsystem.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


@Service
public class AppointmentService {

    public final static Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Page<Appointment> getAllAppointment(int page, int size){
        try{
            logger.info("Fetching all appointments - page: {}, size: {}", page, size);
            Pageable pageable = PageRequest.of(page, size);
            return appointmentRepository.findAll(pageable);
        }catch(Exception e){
            logger.error("An error occurred while fetching the appointment: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch appointments", e);
        }
    }
    public Appointment getAppointmentById(Long id){
        try{
            return appointmentRepository.findById(id).orElse(null);
        }catch(Exception e){
            logger.error("An error occurred while getting the appointment by Id {}:{}",id, e.getMessage());
            throw new RuntimeException("Failed to fetch appointment by id", e);
        }
    }
    public Appointment createAppointment(Appointment appointment){
        try{
            logger.info("Creating a new appointment.");
            return appointmentRepository.save(appointment);
        }catch(Exception e){
            logger.error("An error occurred while creating a new appointment:{}", e.getMessage());
            throw new RuntimeException("Failed to create appointment", e);
        }
    }
    public void deleteAppointment(Long id){
        try{
            logger.info("Deleting appointment with id: {}", id);
            appointmentRepository.deleteById(id);
        }catch(Exception e){
            logger.error("An error occurred while deleteing the appointment:{}", e.getMessage());
            throw new RuntimeException("Failed to delete appointment", e);
        }
    }
    public Appointment updateAppointment(Long id, Appointment updatedAppointment){
        try{
            return appointmentRepository.findById(id)
                    .map(a -> {
                        a.setDoctorId(updatedAppointment.getDoctorId());
                        a.setPatientId(updatedAppointment.getPatientId());
                        a.setDate(updatedAppointment.getDate());
                        return appointmentRepository.save(a);
                    })
                    .orElseGet(() -> {
                        logger.error("Appointment with ID {} is not found.", id);
                        return null;
                    });
        }catch(Exception e){
            logger.error("An error occurred while updating the appointment:{}", e.getMessage());
            throw new RuntimeException("Failed to update appointment", e);
        }
    }

}
