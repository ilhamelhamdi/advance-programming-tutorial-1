package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl extends AbstractRepository<Car> implements CarRepository{

    public Car findByColor(String color){
        return itemData.stream()
                .filter(product -> product.getId().equals(color))
                .findAny()
                .orElseThrow();
    }
}