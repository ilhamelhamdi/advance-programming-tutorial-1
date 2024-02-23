package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public class CarRepositoryImpl<T extends Car> extends ProductRepositoryImpl<T> implements CarRepository<T> {
    @Override
    public T findByColor(String id) {
        return productData.stream()
                .filter(product -> product.getId().equals(id))
                .findAny()
                .orElseThrow();
    }
}
