package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Bill;
import com.javaspring.hospitalmanagementsystem.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<Page<Bill>> getAllBills(@RequestParam(defaultValue = "0")int page,
                                                  @RequestParam(defaultValue = "3")int size){
        logger.info("Fetching all bills.");
        return ResponseEntity.ok(billService.getAllBill(page, size));
    }
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill){
        logger.info("Creating a new bill for patientid: {}", bill.getPatientId());
        return new ResponseEntity<>(billService.createBill(bill), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id){
        logger.info("Fetching bill by id: {}", id);
        Bill bill = billService.getBillById(id);
        if (bill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bill);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        logger.info("Deleting bill with id: {}", id);
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBill(@PathVariable Long id, @RequestBody Bill bill){
        logger.info("Updating bill with id: {}", id);
        billService.updateBill(id, bill);
        return ResponseEntity.ok().build();
    }
}
