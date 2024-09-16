package com.order.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Generated
    private Integer id;
    private String name;
    private int qty;
    private Double price;
}
