package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService service;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
    }

    @Test
    void testCreateAndFind() {
        service.create(product);
        List<Product> productList = service.findAll();
        assertEquals(1, productList.size());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> productList = service.findAll();
        assertEquals(0, productList.size());
    }

    @Test
    void testFindAllIfMoreThanOne() {
        for (int i = 0; i < 5; i++) {
            service.create(product);
        }
        List<Product> productList = service.findAll();
        assertEquals(5, productList.size());
    }

    @Test
    void testFindById() {
        service.create(product);
        Product savedProduct = service.findById(product.getId());
        assertEquals(product.getId(), savedProduct.getId());
    }

    @Test
    void testFindByIdIfNotFound() {
        assertThrows(NoSuchElementException.class, () -> {
            Product savedProduct = service.findById(product.getId());
        });
    }

    @Test
    void testUpdate() {
        service.create(product);

        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setQuantity(product.getQuantity() + 20);

        service.update(product.getId(), newProduct);
        Product updatedProduct = service.findById(product.getId());

        assertEquals(newProduct.getQuantity(), updatedProduct.getQuantity());
    }

    @Test
    void testUpdateIfNotFound() {
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setQuantity(product.getQuantity() + 20);

        assertThrows(NoSuchElementException.class, () -> {
            service.update(product.getId(), newProduct);
        });
    }

    @Test
    void testDelete(){
        service.create(product);
        List<Product> beforeDeleteProducts = service.findAll();
        service.delete(product.getId());
        List<Product> afterDeleteProducts = service.findAll();

        assertEquals(1, beforeDeleteProducts.size());
        assertEquals(0, afterDeleteProducts.size());
    }

}
