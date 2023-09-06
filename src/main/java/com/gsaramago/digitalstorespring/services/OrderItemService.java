package com.gsaramago.digitalstorespring.services;

import com.gsaramago.digitalstorespring.model.Order;
import com.gsaramago.digitalstorespring.model.OrderItem;
import com.gsaramago.digitalstorespring.model.Product;
import com.gsaramago.digitalstorespring.repositories.OrderItemRepository;
import com.gsaramago.digitalstorespring.repositories.OrderRepository;
import com.gsaramago.digitalstorespring.repositories.ProductRepository;
import com.gsaramago.digitalstorespring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public List<OrderItem> findAll(){
        return orderItemRepository.findAll();
    }
    public OrderItem findById(Long id){
        return orderItemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("OrderItem not found."));
    }

    public OrderItem createOrderItem(Long orderId, Long productId, Integer quantity){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        OrderItem orderItem = new OrderItem(order, product,quantity,product.getPrice());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found"));

        Product product = productRepository
                .findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));

        List<OrderItem> orderItem = orderItemRepository.findByIdOrder(order)
                .stream()
                .filter(o->o.getProduct().equals(product))
                .collect(Collectors.toList());

        orderItemRepository.deleteAll(orderItem);
    }
}
