package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Product {
    private String id;
    private String name;
    private int quantity;

    public Product(){
        this.id = UUID.randomUUID().toString();
    }
}
