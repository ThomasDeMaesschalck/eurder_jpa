package com.switchfully.eurder.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class OrderlineTest {

    @Test
    @DisplayName("Orderline total calculation works")
    void whenCalculatingOrderLineTotal_ValueIsCalculated() {
        Orderline orderline = Orderline.builder()
                .amount(5)
                .salePrice(BigDecimal.valueOf(5))
                .build();

        BigDecimal expected = BigDecimal.valueOf(25);

        BigDecimal result = orderline.getOrderlineTotal();

        Assertions.assertEquals(expected, result);
    }
}