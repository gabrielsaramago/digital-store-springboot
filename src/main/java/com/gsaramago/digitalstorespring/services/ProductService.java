package com.gsaramago.digitalstorespring.services;

import com.gsaramago.digitalstorespring.model.Product;
import com.gsaramago.digitalstorespring.repositories.ProductRepository;
import com.gsaramago.digitalstorespring.services.exceptions.DatabaseException;
import com.gsaramago.digitalstorespring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = findById(id);
        if(product.getOrders().isEmpty()){
            productRepository.deleteById(id);
        }
        else {
            throw new DatabaseException("Not allowed deleting products with orders");
        }
    }

}
