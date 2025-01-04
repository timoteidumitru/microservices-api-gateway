package com.payment.api.service;

import com.payment.api.model.Payment;
import com.payment.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    public Optional<Payment> getPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    // Method to find payment by orderId
    public Optional<Payment> getPaymentByOrderId(UUID orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    // Add new payment
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Update payment
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
