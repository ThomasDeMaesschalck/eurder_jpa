/*
package com.switchfully.eurder.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class OrderTest {

    private Order order;
    private Orderline orderline1;
    private Orderline orderline2;

    @BeforeEach
    void setUp() {
        order = new Order(UUID.randomUUID());
        orderline1 = new Orderline(UUID.randomUUID(), "Product", "Description", BigDecimal.valueOf(5), 5, LocalDate.now());
        orderline2 = new Orderline(UUID.randomUUID(), "Product", "Description", BigDecimal.valueOf(50), 10, LocalDate.now());

    }

    @Test
    @DisplayName("Total order price gets calculated correctly")
    void getTotalOrderPrice_ReturnsCorrectAmount() {
        order.addOrderline(orderline1);
        order.addOrderline(orderline2);

        BigDecimal expected = BigDecimal.valueOf(525);
        BigDecimal result = order.getTotalPriceOfOrder();

        Assertions.assertEquals(expected, result);
    }
}
*/
