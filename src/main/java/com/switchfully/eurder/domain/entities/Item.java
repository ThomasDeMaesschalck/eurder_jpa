package com.switchfully.eurder.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    private Item(String name, String description, BigDecimal price, int amountInStock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public UUID getId() {
        return id;
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

    protected static class ItemBuilder {
        private String name;
        private String description;
        private BigDecimal price;
        private int amountInStock;

        protected static ItemBuilder item() {
            return new Item.ItemBuilder();
        }

        protected Item build() {
            return new Item(name, description, price, amountInStock);
        }

        protected Item.ItemBuilder withName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Item name is required");
            }
            this.name = name;
            return this;
        }

        protected Item.ItemBuilder withDescription(String description) {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Item description is required");
            }
            this.description = description;
            return this;
        }

        protected Item.ItemBuilder withPrice(BigDecimal price) {
            if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Item price required and must be greater than 0");
            }
            this.price = price;
            return this;
        }

        protected Item.ItemBuilder withAmountInStock(int amountInStock) {
            if (amountInStock < 0) {
                throw new IllegalArgumentException("Amount in stock can't be negative");
            }
            this.amountInStock = amountInStock;
            return this;
        }
    }
}
