package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentData = new ArrayList<>();
    public Payment save(Payment payment){
        int i = 0;
        for(Payment savedPayment: paymentData){
            // Update
            if(savedPayment.getId().equals(payment.getId())){
                paymentData.remove(i);
                paymentData.add(i,payment);
                return payment;
            }
            i += 1;
        }
        // Create
        paymentData.add(payment);
        return payment;
    }
    public Payment findById(String id){
        for(Payment payment: paymentData){
            if(payment.getId().equals(id)){
                return payment;
            }
        }
        return null;
    }
    public List<Payment> findAll(){
        return paymentData;
    }
}
