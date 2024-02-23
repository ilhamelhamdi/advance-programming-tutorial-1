package id.ac.ui.cs.advprog.eshop.repository;

public interface CarRepository<T> extends ProductRepository<T>{
    T findByColor(String id);
}
