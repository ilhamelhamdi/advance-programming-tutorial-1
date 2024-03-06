package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

    List<Payment> paymentList;

    @BeforeEach
    void setUp() {
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

        this.paymentList = new ArrayList<>();
        Payment payment1 = new Payment(order, PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Payment payment2 = new Payment(order, PaymentMethod.BANK_TRANSER.getValue(), paymentData2);
        paymentList.add(payment1);
        paymentList.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = paymentList.getFirst();
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getOrder(), result.getOrder());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(PaymentStatus.WAITING.getValue(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testSetStatusWithSuccessStatus() {
        Payment payment = paymentList.getFirst();
        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), PaymentStatus.SUCCESS.getValue(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusWithRejectedStatus() {
        Payment payment = paymentList.getFirst();
        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), result.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusWithInvalidStatus() {
        Payment payment = paymentList.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "MEOW"));
    }

    @Test
    void testSetStatusIfPaymentNotFound() {
        Payment notSavedPayment = paymentList.getFirst();
        doReturn(null).when(paymentRepository).findById(notSavedPayment.getId());

        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(notSavedPayment, PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = paymentList.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        doReturn(new ArrayList<Payment>()).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllPaymentsIfNotEmpty() {
        doReturn(paymentList).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        assertEquals(paymentList.size(), result.size());
    }

}
