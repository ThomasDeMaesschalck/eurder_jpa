package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.domain.entities.Orderline;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderlineMapper {

    public Orderline toEntity(Item item, int amount, LocalDate shippingDate) {
        return new Orderline(item.getId(), item.getName(), item.getDescription(), item.getPrice(), amount, shippingDate);
    }
}
