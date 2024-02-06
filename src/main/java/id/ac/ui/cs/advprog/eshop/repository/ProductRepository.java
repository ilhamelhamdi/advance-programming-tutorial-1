package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productaData = new ArrayList<>();

    public Product create(Product product){
        productaData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return  productaData.iterator();
    }
}