package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ProductRepositoryImpl implements ProductRepository {
    final protected List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getId().equals(productId))
                .findAny()
                .orElseThrow();
    }

    public void delete(String productId) {
        productData.removeIf(product -> product.getId().equals(productId));
    }

    public Product update(String productId, Product newProduct) {
        Product oldProduct = productData.stream()
                .filter(product -> product.getId().equals(productId)).
                findAny()
                .orElseThrow();
        int productIndex = productData.indexOf(oldProduct);
        productData.set(productIndex, newProduct);
        return newProduct;
    }
}
