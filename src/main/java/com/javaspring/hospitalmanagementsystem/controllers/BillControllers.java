package com.javaspring.hospitalmanagementsystem.controllers;

import com.javaspring.hospitalmanagementsystem.entity.Bill;
import com.javaspring.hospitalmanagementsystem.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/bills")
public class BillControllers {

    @Autowired
    private BillService billService;

    @GetMapping()
    public Page<Bill> getAllAppointment(@RequestParam(defaultValue = "0")int page,
                                        @RequestParam(defaultValue = "3")int size){
        System.out.println("All the Bills.");
        return billService.getAllBill(page, size);
    }
    @PostMapping()
    public Bill createBill(@RequestBody Bill bill){
        System.out.println("Created a new bill.");
        return billService.createBill(bill);
    }
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id){
        System.out.println("Getting bill by id" + id);
        return billService.getBillById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id){
        System.out.println("Bill Deleted" + id);
        billService.deleteBill(id);
    }
    @PutMapping("/{id}")
    public void updateBill(@PathVariable Long id, @RequestBody Bill bill){
        System.out.println("Updated Bill" + id);
        billService.updateBill(id, bill);
    }
}
