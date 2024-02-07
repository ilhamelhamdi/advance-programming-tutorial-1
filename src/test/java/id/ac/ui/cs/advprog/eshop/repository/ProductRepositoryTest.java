package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("910aac58-48df-45e3-a5e4-34417906d8b3");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById("3cf20657-5575-442e-b102-a96021a3112b");
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindByIdIfEmpty() {
        assertThrows(NoSuchElementException.class, () -> productRepository.findById("3cf20657-5575-442e-b102-a96021a3112b"));
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Product product = new Product();
        product.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertThrows(NoSuchElementException.class, () -> productRepository.findById("910aac58-48df-45e3-a5e4-34417906d8b3"));
    }

    @Test
    void testFindByIdIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("910aac58-48df-45e3-a5e4-34417906d8b3");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("155bdeda-2e40-4516-8d21-6ef667b4e7e7");
        product3.setProductName("Kecap Cap Bujang");
        product3.setProductQuantity(150);
        productRepository.create(product3);

        Product savedProduct = productRepository.findById(product2.getProductId());
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testUpdate() {
        Product oldProduct = new Product();
        oldProduct.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        oldProduct.setProductName("Sampo Cap Bambang");
        oldProduct.setProductQuantity(100);
        productRepository.create(oldProduct);

        Product newProduct = new Product();
        newProduct.setProductId(oldProduct.getProductId());
        newProduct.setProductName("Sampo Cap Udin");
        newProduct.setProductQuantity(50);
        productRepository.update(newProduct.getProductId(), newProduct);

        Product updatedProduct = productRepository.findById(newProduct.getProductId());
        assertEquals(updatedProduct.getProductName(), newProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), newProduct.getProductQuantity());
    }

    @Test
    void testUpdateIfIdNotFound() {
        Product product1 = new Product();
        product1.setProductId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("910aac58-48df-45e3-a5e4-34417906d8b3"); // different id
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        assertThrows(NoSuchElementException.class, () -> productRepository.update(product2.getProductId(), product2));
    }


}
