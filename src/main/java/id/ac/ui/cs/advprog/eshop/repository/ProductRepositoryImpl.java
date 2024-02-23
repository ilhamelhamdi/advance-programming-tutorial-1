package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ProductRepositoryImpl<T extends Product> implements ProductRepository<T> {
    final private List<T> productData = new ArrayList<>();

    public T create(T product) {
        productData.add(product);
        return product;
    }

    public Iterator<T> findAll() {
        return productData.iterator();
    }

    public T findById(String productId) {
        return productData.stream()
                .filter(product -> product.getId().equals(productId))
                .findAny()
                .orElseThrow();
    }

    public void delete(String productId) {
        productData.removeIf(product -> product.getId().equals(productId));
    }

    public T update(String productId, T newProduct) {
        T oldProduct = productData.stream()
                .filter(product -> product.getId().equals(productId)).
                findAny()
                .orElseThrow();
        int productIndex = productData.indexOf(oldProduct);
        productData.set(productIndex, newProduct);
        return newProduct;
    }
}
