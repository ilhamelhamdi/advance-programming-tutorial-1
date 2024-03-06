package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order, method, paymentData);
        return paymentRepository.save(payment);
    }

    public Payment setStatus(Payment payment, String status) {
        Payment savedPayment = paymentRepository.findById(payment.getId());
        if (savedPayment == null) {
            throw new NoSuchElementException("Payment not found");
        }
        payment.setStatus(status);
        if (PaymentStatus.SUCCESS.getValue().equals(status)) {
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }
        return paymentRepository.save(payment);
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
