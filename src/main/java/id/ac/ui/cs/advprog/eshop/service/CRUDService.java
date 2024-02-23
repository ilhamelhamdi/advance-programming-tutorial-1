package id.ac.ui.cs.advprog.eshop.service;

import java.util.Iterator;
import java.util.List;

public interface CRUDService<T> {
    T create(T item);
    T findById(String id);
    List<T> findAll();
    T update(String id, T updatedItem);
    void delete(String id);
}

