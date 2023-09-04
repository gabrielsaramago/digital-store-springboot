package com.gsaramago.digitalstorespring.repositories;

import com.gsaramago.digitalstorespring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
