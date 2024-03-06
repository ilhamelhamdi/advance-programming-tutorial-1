package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> paymentList;

    @BeforeEach
    void setUp(){
        this.paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", products, 1708560000L, "Safira Sudrajat");
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BINI");
        paymentData2.put("referenceCode", "f247ca23-d51a-4209-9eba-8db5a9c057e2");

        Payment payment1 = new Payment(order, PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Payment payment2 = new Payment(order, PaymentMethod.BANK_TRANSER.getValue(), paymentData2);
        paymentList.add(payment1);
        paymentList.add(payment2);
    }

    @Test
    void testSaveForCreate(){
        Payment payment = paymentList.getFirst();
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(paymentList.getFirst().getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveForUpdate(){
        Payment payment = paymentList.getFirst();
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(newPayment.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(paymentList.getFirst().getId());
        assertEquals(paymentList.getFirst().getId(), findResult.getId());
        assertEquals(paymentList.getFirst().getOrder(), findResult.getOrder());
        assertEquals(paymentList.getFirst().getMethod(), findResult.getMethod());
        assertEquals(paymentList.getFirst().getStatus(), findResult.getStatus());
        assertEquals(paymentList.getFirst().getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }
        Payment payment = paymentRepository.findById("zczc");
        assertNull(payment);
    }

    @Test
    void testFindAllIfEmpty(){
        List<Payment> payments = paymentRepository.findAll();
        assertTrue(payments.isEmpty());
    }

    @Test
    void testFindAllIfNotEmpty(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }
        List<Payment> payments = paymentRepository.findAll();
        assertEquals(2, payments.size());
    }
}
