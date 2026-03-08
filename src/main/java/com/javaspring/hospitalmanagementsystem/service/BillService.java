package com.javaspring.hospitalmanagementsystem.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.javaspring.hospitalmanagementsystem.entity.Bill;
import com.javaspring.hospitalmanagementsystem.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
public class BillService {

    public static final Logger logger = LoggerFactory.getLogger(BillService.class);

    @Autowired
    private BillRepository billRepository;

    public Page<Bill> getAllBill(int page, int size){
        try{
            System.out.println("Bill Service layer.");
            Pageable pageable = PageRequest.of(page, size);
            return billRepository.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while fetching all the Bill:{}", e.getMessage());
            return null;
        }
    }
    public Bill getBillById(Long id){
        try{
            Optional<Bill> bill = billRepository.findById(id);
            return bill.orElse(null);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while getting the Bill by Id {}:{}", id, e.getMessage());
            return null;
        }
    }
    public Bill createBill(Bill bill){
        try{
            return billRepository.save(bill);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while creating a new Bill:{}", e.getMessage());
            return null;
        }
    }
    public void deleteBill(Long id){
        try{
            logger.info("Deleting bill with id: {}", id);
            billRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while deleting the Bill:{}", e.getMessage());
        }
    }
    public Bill updateBill(Long id, Bill updatedBill){
        try{
            Optional<Bill> existingBill = billRepository.findById(id);
            if(existingBill.isPresent()){
                Bill b = existingBill.get();
                b.setAmount(updatedBill.getAmount());
                b.setStatus(updatedBill.getStatus());
                b.setPatientId(updatedBill.getPatientId());
                return billRepository.save(b);
            }else{
                logger.error("Bill with ID {} not found", id);
                return null;
            }
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            logger.error("An error occurred while updating the Bill:{}", e.getMessage());
            return  null;
        }
    }

}
