package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId("40f52876-5e83-423c-bdb4-e5bf314a513a");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct(){
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderDefaultStatus()
    {
        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getName());
        assertEquals("Sampo Cap Usep", order.getProducts().get(1).getName());

        assertEquals("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus(){
        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus(){
        assertThrows(IllegalArgumentException.class, ()->{
            Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled(){
        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus(OrderStatus.CANCELLED.getValue());
        assertEquals(OrderStatus.CANCELLED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus(){
        Order order = new Order("8de1c156-c3de-46ca-bf84-c5ee0fb6b0de", this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class,() -> order.setStatus("MEOW"));
    }

}
