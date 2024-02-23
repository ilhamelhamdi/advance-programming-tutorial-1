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
    ProductRepository<Product> productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("910aac58-48df-45e3-a5e4-34417906d8b3");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById("3cf20657-5575-442e-b102-a96021a3112b");
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindByIdIfEmpty() {
        assertThrows(NoSuchElementException.class, () -> productRepository.findById("3cf20657-5575-442e-b102-a96021a3112b"));
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Product product = new Product();
        product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        assertThrows(NoSuchElementException.class, () -> productRepository.findById("910aac58-48df-45e3-a5e4-34417906d8b3"));
    }

    @Test
    void testFindByIdIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("910aac58-48df-45e3-a5e4-34417906d8b3");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setId("155bdeda-2e40-4516-8d21-6ef667b4e7e7");
        product3.setName("Kecap Cap Bujang");
        product3.setQuantity(150);
        productRepository.create(product3);

        Product savedProduct = productRepository.findById(product2.getId());
        assertEquals(product2.getId(), savedProduct.getId());
        assertEquals(product2.getName(), savedProduct.getName());
        assertEquals(product2.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testUpdate() {
        Product oldProduct = new Product();
        oldProduct.setId("3cf20657-5575-442e-b102-a96021a3112b");
        oldProduct.setName("Sampo Cap Bambang");
        oldProduct.setQuantity(100);
        productRepository.create(oldProduct);

        Product newProduct = new Product();
        newProduct.setId(oldProduct.getId());
        newProduct.setName("Sampo Cap Udin");
        newProduct.setQuantity(50);
        productRepository.update(newProduct.getId(), newProduct);

        Product updatedProduct = productRepository.findById(newProduct.getId());
        assertEquals(updatedProduct.getName(), newProduct.getName());
        assertEquals(updatedProduct.getQuantity(), newProduct.getQuantity());
    }

    @Test
    void testUpdateIfIdNotFound() {
        Product product1 = new Product();
        product1.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("910aac58-48df-45e3-a5e4-34417906d8b3"); // different id
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);

        assertThrows(NoSuchElementException.class, () -> productRepository.update(product2.getId(), product2));
    }

    @Test
    void testDelete(){
        Product product = new Product();
        product.setId("3cf20657-5575-442e-b102-a96021a3112b");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getId());
        assertThrows(NoSuchElementException.class, () -> productRepository.findById(product.getId()));
    }

    @Test
    void testDeleteIfIdNotFound(){
        assertThrows(NoSuchElementException.class, () -> productRepository.delete("3cf20657-5575-442e-b102-a96021a3112b"));
    }
}
