package com.switchfully.eurder.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OrderlineDTO {

    private final UUID itemId;
    private final BigDecimal salesPrice;
    private final int amount;
    private final BigDecimal subTotal;
    private final LocalDate shippingDate;

    public OrderlineDTO(UUID itemId, BigDecimal salesPrice, int amount, BigDecimal subTotal, LocalDate shippingDate) {
        this.itemId = itemId;
        this.salesPrice = salesPrice;
        this.amount = amount;
        this.subTotal = subTotal;
        this.shippingDate = shippingDate;
    }

    public UUID getItemId() {
        return itemId;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
