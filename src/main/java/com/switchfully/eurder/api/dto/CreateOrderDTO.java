package com.switchfully.eurder.api.dto;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {

    private final UUID customerId;
    private final List<CreateOrderlineDTO> orderlines;

    public CreateOrderDTO(UUID customerId, List<CreateOrderlineDTO> orderlines) {
        this.customerId = customerId;
        this.orderlines = orderlines;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<CreateOrderlineDTO> getOrderlines() {
        return orderlines;
    }
}
