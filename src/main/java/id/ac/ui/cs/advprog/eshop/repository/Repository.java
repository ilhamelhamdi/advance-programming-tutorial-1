package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface Repository<T> {
    T create(T item);
    Iterator<T> findAll();
    T findById(String itemId);
    void delete(String itemId);
    T update(String itemId, T newItem);

}
