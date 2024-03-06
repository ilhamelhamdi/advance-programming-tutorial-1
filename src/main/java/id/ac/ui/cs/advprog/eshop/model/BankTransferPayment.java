package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class BankTransferPayment extends Payment{
    BankTransferPayment(Order order, Map<String, String> paymentData){
        super(order, PaymentMethod.BANK_TRANSER.getValue(), paymentData);
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if(bankName == null || bankName.isEmpty()){
            setStatus(PaymentStatus.REJECTED.getValue());
        } else if (referenceCode == null || referenceCode.isEmpty()){
            setStatus(PaymentStatus.REJECTED.getValue());
        } else  {
            setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
