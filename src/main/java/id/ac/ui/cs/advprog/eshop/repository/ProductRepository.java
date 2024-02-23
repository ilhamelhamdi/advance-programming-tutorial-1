package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepository {
    Product create(Product item);
    Product findById(String id);
    Iterator<Product> findAll();
    Product update(String id, Product updatedItem);
    void delete(String id);
}
