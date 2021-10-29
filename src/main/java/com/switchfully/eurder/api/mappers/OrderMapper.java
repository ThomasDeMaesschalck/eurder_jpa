package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.orders.OrderDTO;
import com.switchfully.eurder.api.dto.orders.OrderlineDTO;
import com.switchfully.eurder.domain.entities.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {

        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCustomerId(), order.getTotalPriceOfOrder());

        order.getOrderlines().stream()
                .map(orderline -> new OrderlineDTO(orderline.getItemId(), orderline.getSalePrice(), orderline.getAmount(), orderline.getOrderlineTotal(), orderline.getShippingDate()))
                .forEach(orderDTO::addOrderlineDTO);

        return orderDTO;
    }

    public Order toEntity(UUID customerId){
        return new Order(customerId);
    }

}
