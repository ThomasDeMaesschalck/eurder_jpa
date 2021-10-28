package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.CreateOrderDTO;
import com.switchfully.eurder.api.dto.CreateOrderlineDTO;
import com.switchfully.eurder.api.dto.ItemDTO;
import com.switchfully.eurder.api.mappers.OrderMapper;
import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.domain.entities.Order;
import com.switchfully.eurder.domain.entities.Orderline;
import com.switchfully.eurder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserService userService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, UserService userService, ItemService itemService, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }


    public ItemDTO save(UUID userId, CreateOrderDTO createOrderDTO) {
        userService.assertUserId(userId);

        Order order = new Order(userId);

        for (CreateOrderlineDTO orderline : createOrderDTO.getOrderlines()) {
            itemService.assertItemId(orderline.getItemId());

            Item itemToAdd = itemService.getById(orderline.getItemId());

            //TODO: lower stock amount when processing order
            //TODO: LocalDate shipping date aanpassen
            //TODO: amount must be greater than zero
            //TODO: return DTO

            Orderline orderlineToAdd = new Orderline(itemToAdd.getId(), itemToAdd.getName(), itemToAdd.getDescription(), itemToAdd.getPrice(), orderline.getAmount(), LocalDate.now().plusDays(1));
            order.addOrderline(orderlineToAdd);
        }
        orderRepository.save(order);

        return null;
    }
}
