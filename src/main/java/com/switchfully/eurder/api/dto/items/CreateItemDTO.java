package com.switchfully.eurder.api.dto.items;

import java.math.BigDecimal;

public class CreateItemDTO {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;

    private CreateItemDTO(String name, String description, BigDecimal price, int amountInStock) {
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

    public static class CreateItemDTOBuilder {
        private String name;
        private String description;
        private BigDecimal price;
        private int amountInStock;

        public static CreateItemDTO.CreateItemDTOBuilder item() {
            return new CreateItemDTO.CreateItemDTOBuilder();
        }

        public CreateItemDTO build() {
            return new CreateItemDTO(name, description, price, amountInStock);
        }


        public CreateItemDTO.CreateItemDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateItemDTO.CreateItemDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateItemDTO.CreateItemDTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CreateItemDTO.CreateItemDTOBuilder withAmountInStock(int amountInStock) {
            this.amountInStock = amountInStock;
            return this;
        }
    }
}
