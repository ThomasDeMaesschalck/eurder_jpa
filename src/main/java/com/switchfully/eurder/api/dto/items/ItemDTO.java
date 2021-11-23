package com.switchfully.eurder.api.dto.items;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    private ItemDTO(Long id, String name, String description, BigDecimal price, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public Long getId() {
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

    public static class ItemDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private int amountInStock;

        public static ItemDTOBuilder item() {
            return new ItemDTOBuilder();
        }

        public ItemDTO build() {
            return new ItemDTO(id, name, description, price, amountInStock);
        }

        public ItemDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ItemDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemDTOBuilder withAmountInStock(int amountInStock) {
            this.amountInStock = amountInStock;
            return this;
        }
    }
}
