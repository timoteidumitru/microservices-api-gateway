package com.order.api.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendPayment {
    private String paymentStatus;
    private String transactionId;
    private UUID orderId;
    private double amount;
}
