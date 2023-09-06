package com.gsaramago.digitalstorespring.repositories;

import com.gsaramago.digitalstorespring.model.Order;
import com.gsaramago.digitalstorespring.model.OrderItem;
import com.gsaramago.digitalstorespring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByIdOrder(Order order);

    List<OrderItem> findByIdProduct(Product product);

}
