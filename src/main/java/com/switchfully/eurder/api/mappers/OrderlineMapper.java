package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.domain.entities.Order;
import com.switchfully.eurder.domain.entities.Orderline;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderlineMapper {

    public Orderline toEntity(Item item, int amount, LocalDate shippingDate, Order order) {
        return Orderline.builder()
                .order(order)
                .item(item)
                .name(item.getName())
                .description(item.getDescription())
                .amount(amount)
                .salePrice(item.getPrice())
                .shippingDate(shippingDate)
                .build();
    }
}
