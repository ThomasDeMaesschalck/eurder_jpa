package com.switchfully.eurder.domain.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID customerId;
    private final Set<Orderline> orderlines;

    public Order(UUID customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderlines = new HashSet<>();
    }

    public void addOrderline(Orderline orderline) {
        orderlines.add(orderline);
    }

    public BigDecimal getTotalPriceOfOrder() {
        return orderlines.stream()
                .map(Orderline::getOrderlineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Set<Orderline> getOrderlines() {
        return orderlines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
