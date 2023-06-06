/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ut7pro.ut7pro.controllers;

import com.ut7pro.ut7pro.exceptions.NotFoundException;
import com.ut7pro.ut7pro.models.Orders;
import com.ut7pro.ut7pro.services.OrderService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController 
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return orderservice.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        try {
            Orders orders = orderservice.getOrderById(id);
            return ResponseEntity.ok(orders);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        Orders createdOrders = orderservice.createOrders(orders);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + createdOrders.getId())).body(createdOrders);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id, @RequestBody Orders orders) {
        Orders updatedOrders = orderservice.updateOrders(orders);
        return ResponseEntity.ok(updatedOrders);
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<Orders> updatePatchOrder(@PathVariable("id") Long id, @RequestBody Orders orders) {
        Orders patchedOrders = orderservice.updateOrders(orders);
        return ResponseEntity.ok(patchedOrders);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderservice.deleteOrders(id);
        return ResponseEntity.noContent().build();
    }
}
