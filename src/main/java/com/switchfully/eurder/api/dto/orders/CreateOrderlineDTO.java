package com.switchfully.eurder.api.dto.orders;

import java.util.UUID;

public class CreateOrderlineDTO {

    private final Long ItemId;
    private final int amount;

    public CreateOrderlineDTO(Long itemId, int amount) {
        ItemId = itemId;
        this.amount = amount;
    }

    public Long getItemId() {
        return ItemId;
    }

    public int getAmount() {
        return amount;
    }
}
