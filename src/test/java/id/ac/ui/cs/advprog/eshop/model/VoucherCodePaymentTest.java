package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class VoucherCodePaymentTest {
    Order order;
    Map<String, String> validPaymentData;

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
        this.order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", products, 1708560000L, "Safira Sudrajat");

        validPaymentData = new HashMap<>();
        validPaymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testMethod(){
        Payment payment = new VoucherCodePayment(this.order, this.validPaymentData);
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
    }

    @Test
    void testPaymentDataKey(){
        Payment payment = new VoucherCodePayment(this.order, this.validPaymentData);
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
    }

    @Test
    void testPaymentDataKeyWithInvalidDataKey(){
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("bankName", "BINI");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new VoucherCodePayment(this.order, invalidPaymentData);
        });
    }

    @Test
    void testPaymentDataWithValidVoucherCode(){
        Payment payment = new VoucherCodePayment(this.order, this.validPaymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataWithWrongSizeVoucherCode(){
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "ESHOP1234ABC5678A");
        Payment payment = new VoucherCodePayment(this.order, invalidPaymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataWithVoucherCodeNotStartedESHOP(){
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "ABCDE1234ABC5678");
        Payment payment = new VoucherCodePayment(this.order, invalidPaymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataWithVoucherCodeWrongNumericalChars(){
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "ESHOP12345678901");
        Payment payment = new VoucherCodePayment(this.order, invalidPaymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
