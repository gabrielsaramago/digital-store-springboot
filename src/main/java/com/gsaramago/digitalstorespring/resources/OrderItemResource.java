package com.gsaramago.digitalstorespring.resources;

import com.gsaramago.digitalstorespring.model.Order;
import com.gsaramago.digitalstorespring.model.OrderItem;
import com.gsaramago.digitalstorespring.model.Product;
import com.gsaramago.digitalstorespring.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orderItems")
public class OrderItemResource {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> findAll(){
        List<OrderItem> list = orderItemService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id){
        OrderItem orderItem = orderItemService.findById(id);
        return ResponseEntity.ok().body(orderItem);
    }

    @PostMapping(value = "/{orderId}/{productId}")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long orderId, @PathVariable Long productId){
        orderItem = orderItemService.createOrderItem(orderId, productId,orderItem.getQuantity());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("/{id}").toUri();
        return ResponseEntity.ok().body(orderItem);

    }

    @DeleteMapping(value = "/{orderId}/{productId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long productId){
        orderItemService.deleteOrderItem(orderId, productId);
        return ResponseEntity.noContent().build();
    }

}
