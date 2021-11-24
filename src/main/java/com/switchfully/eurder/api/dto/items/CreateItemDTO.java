package com.switchfully.eurder.api.dto.items;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Builder
public class CreateItemDTO {
    @NotBlank(message = "Name can not be empty")
    @NotNull
    private final String name;

    @NotBlank(message = "Description can not be empty")
    @NotNull
    private final String description;

    @Positive(message = "Price needs to be greater than zero")
    private final BigDecimal price;

    @PositiveOrZero(message = "Stock can't be negative")
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


}
