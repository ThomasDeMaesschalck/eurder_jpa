package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.*;
import com.switchfully.eurder.api.mappers.OrderMapper;
import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.domain.entities.Order;
import com.switchfully.eurder.domain.entities.Orderline;
import com.switchfully.eurder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class OrderService {

    private static final int IN_STOCK_SHIPPING_DAYS = 1;
    private static final int BACKORDERED_SHIPPING_DAYS = 7;

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


    public OrderDTO save(UUID userId, CreateOrderDTO createOrderDTO) {
        userService.assertUserId(userId);

        Order order = processOrder(createOrderDTO, userId);
        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    private Order processOrder(CreateOrderDTO createOrderDTO, UUID userId) {

        createOrderDTO.getOrderlines().forEach(orderline -> {
            itemService.assertItemId(orderline.getItemId());
            assertOrderlineAmountGreaterThanZero(orderline.getAmount());
        });

        Order order = new Order(userId);

        createOrderDTO.getOrderlines().forEach(orderline -> {
            Orderline orderlineToAdd = processOrderline(orderline);
            order.addOrderline(orderlineToAdd);
        });

        return order;
    }

    private Orderline processOrderline(CreateOrderlineDTO orderline) {

        Item item = itemService.getById(orderline.getItemId());

        item.takeItemsFromStock(orderline.getAmount());

        return new Orderline(item.getId(), item.getName(), item.getDescription(), item.getPrice(), orderline.getAmount(), calculateShippingDate(orderline.getAmount(), item.getAmountInStock()));
    }

    private LocalDate calculateShippingDate(int amountOrdered, int amountInStock) {
        if (amountInStock >= amountOrdered) {
            return LocalDate.now().plusDays(IN_STOCK_SHIPPING_DAYS);
        } else {
            return LocalDate.now().plusDays(BACKORDERED_SHIPPING_DAYS);
        }
    }

    private void assertOrderlineAmountGreaterThanZero(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("You can't order 0 or less of an item.");
        }
    }
}
