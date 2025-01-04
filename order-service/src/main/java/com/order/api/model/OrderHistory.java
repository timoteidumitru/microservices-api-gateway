package com.order.api.model;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderHistory {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    // Fields from Order entity
    private String orderName;
    private int qty;
    private Double price;
    private String paymentStatus;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
