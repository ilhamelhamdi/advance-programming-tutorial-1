package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface Repository<T> {
    T create(T item);
    T findById(String id);
    Iterator<T> findAll();
    T update(String id, T updatedItem);
    void delete(String id);
}
