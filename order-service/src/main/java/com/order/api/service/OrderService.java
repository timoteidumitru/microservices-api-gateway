package com.order.api.service;

import com.order.api.dao.SendPayment;
import com.order.api.model.Order;
import com.order.api.model.OrderHistory;
import com.order.api.repository.OrderRepository;
import com.order.api.service.external.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentService paymentService;

    // Get all orders
    public List<OrderHistory> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        List<OrderHistory> orderHistories = new ArrayList<>();

        for (Order order : allOrders) {
            // Fetch payment details by orderId
            SendPayment payment = paymentService.findByOrderId(order.getId());

            // Create a new OrderHistory object with combined details
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setId(order.getId());
            orderHistory.setOrderName(order.getName());
            orderHistory.setQty(order.getQty());
            orderHistory.setPrice(order.getPrice());

            if (payment != null) {
                orderHistory.setPaymentStatus(payment.getPaymentStatus());
            }
            orderHistories.add(orderHistory);
        }

        return orderHistories;
    }



    // Get order by ID
    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order addOrder(Order order) {
        try {
            Order placedOrder = orderRepository.save(order);
            paymentService.sendPayment(placedOrder);
            return placedOrder;
        } catch (RuntimeException ex) {
            throw new IllegalStateException("Failed to add order due to transactional failure", ex);
        }
    }

    // Update order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete order by ID
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
