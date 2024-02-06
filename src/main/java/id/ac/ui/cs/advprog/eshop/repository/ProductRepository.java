package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findAny()
                .orElseThrow();
    }

    public Product delete(String productId) {
        Product deletedProduct = productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findAny()
                .orElseThrow();
        productData.remove(deletedProduct);
        return deletedProduct;
    }

    public Product update(String productId, Product newProduct) {
        Product oldProduct = productData.stream()
                .filter(product -> product.getProductId().equals(productId)).
                findAny()
                .orElseThrow();
        int productIndex = productData.indexOf(oldProduct);
        productData.set(productIndex, newProduct);
        return newProduct;
    }
}
