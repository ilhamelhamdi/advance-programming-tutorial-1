package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoucherCodePayment extends Payment{
    VoucherCodePayment(Order order, Map<String, String> paymentData){
        super(order, PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        if(!paymentData.containsKey("voucherCode")){
            throw new IllegalArgumentException();
        }

        String voucherKey = paymentData.get("voucherCode");
        Pattern pattern = Pattern.compile("\\\\d{8}");
        Matcher matcher = pattern.matcher(voucherKey);
        if(voucherKey.length() != 16){
            setStatus(PaymentStatus.REJECTED.getValue());
        } else if(!voucherKey.startsWith("ESHOP")){
            setStatus(PaymentStatus.REJECTED.getValue());
        } else if(!containsEightNumericCharacters(voucherKey)){
            setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }

    private boolean containsEightNumericCharacters(String input) {
        int numericCount = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                numericCount++;
            }
        }
        return numericCount == 8;
    }
}
