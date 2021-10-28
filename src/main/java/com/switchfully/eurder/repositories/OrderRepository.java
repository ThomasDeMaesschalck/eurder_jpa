package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final Map<UUID, Order> orders = new HashMap<>();

    public OrderRepository() {
    }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return orders.get(order.getId());
    }

    public List<Order> getOrders() {
        return List.copyOf(orders.values());
    }

    public Order getById(UUID orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with id " + orderId + " not found.");
        }
        return order;
    }
}
