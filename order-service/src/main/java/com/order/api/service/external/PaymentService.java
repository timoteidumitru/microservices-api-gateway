package com.order.api.service.external;

import com.order.api.config.RestTemplateConfig;
import com.order.api.dao.SendPayment;
import com.order.api.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    RestTemplateConfig template;
    @Autowired
    SendPayment sendToPay;

    // Use Eureka service discovery instead of hardcoded URL
    public SendPayment findByOrderId(UUID orderId) {
        try {
            // Use service name "PAYMENT-SERVICE" registered in Eureka
            return template.getRestTemplate()
                    .getForObject("http://PAYMENT-SERVICE/api/payment/" + orderId, SendPayment.class);
        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("Payment not found for orderId: " + orderId);
            return null; // Handle 404 - Not Found
        } catch (Exception ex) {
            System.out.println("Error fetching payment by orderId: " + ex.getMessage());
            return null; // Handle other errors
        }
    }

    public void sendPayment(Order order) {
        sendToPay.setAmount(order.getPrice());
        sendToPay.setOrderId(order.getId());
        sendToPay.setPaymentStatus(orderStatus());
        sendToPay.setTransactionId(UUID.randomUUID().toString());
        try {
            // Use service name "PAYMENT-SERVICE" registered in Eureka
            template.getRestTemplate().postForEntity("http://PAYMENT-SERVICE/api/payment",
                    sendToPay, SendPayment.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String orderStatus() {
        return new Random().nextInt(2) == 1 ? "accepted" : "rejected";
    }
}
