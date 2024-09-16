package com.order.api.service;

import com.order.api.model.Order;
import com.order.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    // Add new order
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // Update order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete order by ID
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
