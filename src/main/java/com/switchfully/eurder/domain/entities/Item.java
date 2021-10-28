package com.switchfully.eurder.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Item {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    private Item(UUID id, String name, String description, BigDecimal price, int amountInStock) {
        if (id == null) {
            this.id = UUID.randomUUID();
        } else {
            this.id = id;
        }
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

    public static class ItemBuilder {
        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private int amountInStock;

        public static ItemBuilder item() {
            return new Item.ItemBuilder();
        }

        public Item buildNewItem() {
            return new Item(null, name, description, price, amountInStock);
        }

        public Item buildUpdatedNewItem() {
            return new Item(id, name, description, price, amountInStock);
        }

        public Item.ItemBuilder withId(UUID id) {
            if (id == null) {
                throw new IllegalArgumentException("Item id is required");
            }
            this.id = id;
            return this;
        }

        public Item.ItemBuilder withName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Item name is required");
            }
            this.name = name;
            return this;
        }

        public Item.ItemBuilder withDescription(String description) {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Item description is required");
            }
            this.description = description;
            return this;
        }

        public Item.ItemBuilder withPrice(BigDecimal price) {
            if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Item price required and must be greater than 0");
            }
            this.price = price;
            return this;
        }

        public Item.ItemBuilder withAmountInStock(int amountInStock) {
            if (amountInStock < 0) {
                throw new IllegalArgumentException("Amount in stock can't be negative");
            }
            this.amountInStock = amountInStock;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
