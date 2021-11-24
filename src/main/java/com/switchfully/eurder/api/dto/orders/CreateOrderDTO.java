package com.switchfully.eurder.api.dto.orders;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {

    private final List<CreateOrderlineDTO> orderlines = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateOrderDTO(List<CreateOrderlineDTO> orderlines) {
        this.orderlines.addAll(orderlines);
    }

    public List<CreateOrderlineDTO> getOrderlines() {
        return orderlines;
    }
}
