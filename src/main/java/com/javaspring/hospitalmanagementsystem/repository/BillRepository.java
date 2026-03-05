package com.javaspring.hospitalmanagementsystem.repository;

import com.javaspring.hospitalmanagementsystem.entity.Bill;
import com.javaspring.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
