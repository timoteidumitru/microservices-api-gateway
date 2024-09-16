package com.payment.api.service;

import com.payment.api.model.Payment;
import com.payment.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    // Add new payment
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Update payment
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Delete payment by ID
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }
}
