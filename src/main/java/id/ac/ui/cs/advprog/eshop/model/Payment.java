package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(Order order, String method, String status, Map<String, String> paymentData){
        this.id = UUID.randomUUID().toString();
        this.setStatus(status);
        this.setOrder(order);
        this.setMethod(method);
        this.setPaymentData(paymentData);
    }
    public Payment(Order order, String method, Map<String, String> paymentData){
        this(order, method, "WAITING", paymentData);
    }
    public void setStatus(String status){
        String[] statusList = {"WAITING", "SUCCESS", "REJECTED"};
        if(Arrays.stream(statusList).noneMatch(item -> item.equals(status))){
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }

    private void setOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order must not be null");
        }
        this.order = order;
    }

    private void setMethod(String method){
        String[] methodList = {"VOUCHER_CODE", "BANK_TRANSFER"};
        if(Arrays.stream(methodList).noneMatch(item -> item.equals(method))){
            throw new IllegalArgumentException("Invalid method");
        }
        this.method = method;
    }

    private void setPaymentData(Map<String, String> paymentData){
        if(paymentData == null || paymentData.isEmpty()){
            throw new IllegalArgumentException("Payment data must not be empty or null");
        }
        this.paymentData = paymentData;
    }
}
