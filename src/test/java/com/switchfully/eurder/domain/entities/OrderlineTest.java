/*
package com.switchfully.eurder.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class OrderlineTest {

    @Test
    @DisplayName("Orderline total calculation works")
    void whenCalculatingOrderLineTotal_ValueIsCalculated() {
        Orderline orderline = new Orderline(UUID.randomUUID(), "Product", "Description", BigDecimal.valueOf(5), 5, LocalDate.now());

        BigDecimal expected = BigDecimal.valueOf(25);

        BigDecimal result = orderline.getOrderlineTotal();

        Assertions.assertEquals(expected, result);
    }
}*/
