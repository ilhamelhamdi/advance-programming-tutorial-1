package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    Payment(Order order, String method, String status, Map<String, String> paymentData){}
    Payment(Order order, String method, Map<String, String> paymentData){}
    void setStatus(String status){}
}
