package com.switchfully.eurder.api.dto.reports;

import java.math.BigDecimal;

public class OrderlineReportDTO {

    private final String itemName;
    private final int amountOrdered;
    private BigDecimal orderlineTotal;

    public OrderlineReportDTO(String itemName, int amountOrdered, BigDecimal orderlineTotal) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.orderlineTotal = orderlineTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public BigDecimal getOrderlineTotal() {
        return orderlineTotal;
    }
}
