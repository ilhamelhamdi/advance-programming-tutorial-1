package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Product> implements Repository<T> {
    final protected List<T> itemData = new ArrayList<>();

    public T create(T item) {
        itemData.add(item);
        return item;
    }

    public Iterator<T> findAll() {
        return itemData.iterator();
    }

    public T findById(String itemId) {
        return itemData.stream().filter(item -> item.getId().equals(itemId)).findAny().orElseThrow();
    }

    public void delete(String itemId) {
        itemData.removeIf(item -> item.getId().equals(itemId));
    }

    public T update(String itemId, T newItem) {
        T oldItem = itemData.stream().filter(item -> item.getId().equals(itemId)).findAny().orElseThrow();
        int productIndex = itemData.indexOf(oldItem);
        itemData.set(productIndex, newItem);
        return newItem;
    }
}
