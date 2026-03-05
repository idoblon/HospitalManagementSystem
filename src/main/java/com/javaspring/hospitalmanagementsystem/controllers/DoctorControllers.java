package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Doctor;
import com.javaspring.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorControllers {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue= "0")int page,
                                     @RequestParam(defaultValue = "3")int size){
        System.out.println("Getting all the doctors.");
        return doctorService.getAllDoctors(page, size);
    }

    @PostMapping("/create")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        System.out.println("New Doctor added.");
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        System.out.println("Doctor By id" + id);
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id){
        System.out.println("Doctor Deleted");
    }

    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id){
        System.out.println("Doctor Updated");
        doctorService.updateDoctor(id);
    }
}
