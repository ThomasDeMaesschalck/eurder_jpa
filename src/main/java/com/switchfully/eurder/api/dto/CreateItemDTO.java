package com.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public class CreateItemDTO {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    public CreateItemDTO(String name, String description, BigDecimal price, int amountInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }
}
