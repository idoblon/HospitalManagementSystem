package com.javaspring.hospitalmanagementsystem.service;

import com.javaspring.hospitalmanagementsystem.entity.Bill;
import com.javaspring.hospitalmanagementsystem.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BillService {

    public static final Logger logger = LoggerFactory.getLogger(BillService.class);

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Page<Bill> getAllBill(int page, int size){
        try{
            logger.info("Fetching all bills - page: {}, size: {}", page, size);
            Pageable pageable = PageRequest.of(page, size);
            return billRepository.findAll(pageable);
        }catch(Exception e){
            logger.error("An error occurred while fetching all the Bills: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch bills", e);
        }
    }
    public Bill getBillById(Long id){
        try{
            return billRepository.findById(id).orElse(null);
        }catch(Exception e){
            logger.error("An error occurred while getting the Bill by Id {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch bill by id", e);
        }
    }
    public Bill createBill(Bill bill){
        try{
            logger.info("Creating a new bill for patientId: {}", bill.getPatientId());
            return billRepository.save(bill);
        }catch(Exception e){
            logger.error("An error occurred while creating a new Bill: {}", e.getMessage());
            throw new RuntimeException("Failed to create bill", e);
        }
    }
    public void deleteBill(Long id){
        try{
            logger.info("Deleting bill with id: {}", id);
            billRepository.deleteById(id);
        }catch(Exception e){
            logger.error("An error occurred while deleting the Bill: {}", e.getMessage());
            throw new RuntimeException("Failed to delete bill", e);
        }
    }
    public Bill updateBill(Long id, Bill updatedBill){
        try{
            return billRepository.findById(id)
                    .map(b -> {
                        b.setAmount(updatedBill.getAmount());
                        b.setStatus(updatedBill.getStatus());
                        b.setPatientId(updatedBill.getPatientId());
                        return billRepository.save(b);
                    })
                    .orElseGet(() -> {
                        logger.error("Bill with ID {} not found", id);
                        return null;
                    });
        }catch(Exception e){
            logger.error("An error occurred while updating the Bill: {}", e.getMessage());
            throw new RuntimeException("Failed to update bill", e);
        }
    }

}
