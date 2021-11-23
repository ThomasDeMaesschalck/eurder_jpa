package com.switchfully.eurder.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Orderline {

    private final Long itemId;
    private final String name;
    private final String description;
    private final BigDecimal salePrice;
    private final int amount;
    private final LocalDate shippingDate;

    public Orderline(Long itemId, String name, String description, BigDecimal salePrice, int amount, LocalDate shippingDate) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.salePrice = salePrice;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderline orderline = (Orderline) o;
        return Objects.equals(itemId, orderline.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public BigDecimal getOrderlineTotal(){
        return salePrice.multiply(BigDecimal.valueOf(amount));
    }
}
