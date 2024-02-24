package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;

    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        this.product.setName("Sampo Cap Bambang");
        this.product.setQuantity(100);
    }

    @Test
    void testGetProductId(){
        assertEquals("3cf20657-5575-442e-b102-a96021a3112b", this.product.getId());
    }

    @Test
    void testGetProductName(){
        assertEquals("Sampo Cap Bambang", this.product.getName());
    }

    @Test
    void testGetProductQuantity(){
        assertEquals(100, this.product.getQuantity());
    }
}
