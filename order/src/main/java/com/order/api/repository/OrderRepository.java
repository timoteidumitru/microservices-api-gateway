package com.order.api.repository;

import com.order.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();

    Optional<Order> findById(Integer id);

    Order save(Order order);

    void deleteById(Integer id);
}
