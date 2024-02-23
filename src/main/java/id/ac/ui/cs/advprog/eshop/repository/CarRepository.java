package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;

public interface CarRepository {
    Car create(Car item);
    Car findById(String id);
    Iterator<Car> findAll();
    Car update(String id, Car updatedItem);
    void delete(String id);
    Car findByColor(String color);
}
