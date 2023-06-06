/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ut7pro.ut7pro.services;

import com.ut7pro.ut7pro.exceptions.NotFoundException;
import com.ut7pro.ut7pro.models.Orders;
import com.ut7pro.ut7pro.repositories.OrderRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrdersByUserId(Long id) {
        return orderRepository.findById(id);
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with ID: "+ id));
    }

    public Orders createOrders(Orders newOrders) {
        return orderRepository.save(newOrders);
    }

    public Orders updateOrders(Orders updatedOrders) {
        return orderRepository.save(updatedOrders);
    }

    public void deleteOrders(Long id) {
        orderRepository.deleteById(id);
    }
}
