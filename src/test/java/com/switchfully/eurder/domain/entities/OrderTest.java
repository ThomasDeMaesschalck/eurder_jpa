package com.switchfully.eurder.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

class OrderTest {

    private Order order;
    private Orderline orderline1;
    private Orderline orderline2;

    @BeforeEach
    void setUp() {
        order = new Order();
        orderline1 =  Orderline.builder()
                .amount(5)
                .salePrice(BigDecimal.valueOf(5))
                .build();
        orderline2 =  Orderline.builder()
                .amount(10)
                .salePrice(BigDecimal.valueOf(50))
                .build();
    }

    @Test
    @DisplayName("Total order price gets calculated correctly")
    void getTotalOrderPrice_ReturnsCorrectAmount() {
        order.setOrderlines(Set.of(orderline1, orderline2));

        BigDecimal expected = BigDecimal.valueOf(525);
        BigDecimal result = order.getTotalPriceOfOrder();

        Assertions.assertEquals(expected, result);
    }
}
