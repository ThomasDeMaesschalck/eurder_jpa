package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.OrderDTO;
import com.switchfully.eurder.api.dto.OrderlineDTO;
import com.switchfully.eurder.domain.entities.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {

        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCustomerId(), order.getTotalPriceOfOrder());

        order.getOrderlines().stream()
                .map(orderline -> new OrderlineDTO(orderline.getItemId(), orderline.getSalePrice(), orderline.getAmount(), orderline.getOrderlineTotal(), orderline.getShippingDate()))
                .forEach(orderDTO::addOrderlineDTO);

        return orderDTO;
    }

}
