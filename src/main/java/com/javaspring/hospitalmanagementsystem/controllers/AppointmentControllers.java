package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Appointment;
import com.javaspring.hospitalmanagementsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentControllers {


    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public Page<Appointment> getAllAppointment(@RequestParam(defaultValue = "0")int page,
                                               @RequestParam(defaultValue = "3")int size){
        System.out.println("Showing the list of the appointments.");
        return appointmentService.getAllAppointment(page, size);
    }
    @PostMapping("")
    public Appointment createAppointment(@RequestBody Appointment appointment){
        System.out.println("Creating a new appointment.");
        return appointmentService.createAppointment(appointment);
    }
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        System.out.println("Appointment by" + id);
        return appointmentService.getAppointmentById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        System.out.println("Appointment deleted " + id);
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        System.out.println("Appointment updated" + id);
        appointmentService.updateAppointment(id, appointment);
    }
}
