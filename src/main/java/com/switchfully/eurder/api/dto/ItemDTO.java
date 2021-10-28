package com.switchfully.eurder.api.dto;

import com.switchfully.eurder.domain.entities.Item;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDTO {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    private ItemDTO(UUID id, String name, String description, BigDecimal price, int amountInStock) {
        this.id = id;
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

        public static ItemDTO.ItemBuilder item() {
            return new ItemDTO.ItemBuilder();
        }

        public ItemDTO build() {
            return new ItemDTO(id, name, description, price, amountInStock);
        }

        public ItemDTO.ItemBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ItemDTO.ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemDTO.ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDTO.ItemBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemDTO.ItemBuilder withAmountInStock(int amountInStock) {
            this.amountInStock = amountInStock;
            return this;
        }
    }
}
