package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoucherCodePayment extends Payment{
    VoucherCodePayment(Order order, Map<String, String> paymentData){
        super(order, PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
    }
}
