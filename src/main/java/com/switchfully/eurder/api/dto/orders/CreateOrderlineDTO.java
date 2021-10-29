package com.switchfully.eurder.api.dto.orders;

import java.util.UUID;

public class CreateOrderlineDTO {

    private final UUID ItemId;
    private final int amount;

    public CreateOrderlineDTO(UUID itemId, int amount) {
        ItemId = itemId;
        this.amount = amount;
    }

    public UUID getItemId() {
        return ItemId;
    }

    public int getAmount() {
        return amount;
    }
}
