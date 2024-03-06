package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId("40f52876-5e83-423c-bdb4-e5bf314a513a");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(1);
        products.add(product1);
        products.add(product2);

        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", products, 1708560000L, "Safira Sudrajat");
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentWithDefaultStatus() {
        Payment payment = new Payment(this.order, "VOUCHER_CODE", this.paymentData);

        assertSame(this.order, payment.getOrder());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("WAITING", payment.getStatus());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithSuccessStatus() {
        Payment payment = new Payment(this.order, "VOUCHER_CODE", "SUCCESS", this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "VOUCHER_CODE", "UHUY", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "DIBAYARIN_TEMEN", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(null, "VOUCHER_CODE", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentWithNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "VOUCHER_CODE", null);
        });
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "VOUCHER_CODE", new HashMap<String,String>());
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment(this.order, "VOUCHER_CODE", this.paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment(this.order, "VOUCHER_CODE", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("DIBAYARIN_TEMEN"));
    }

}
