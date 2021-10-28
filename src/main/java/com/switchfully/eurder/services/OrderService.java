package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.CreateOrderDTO;
import com.switchfully.eurder.api.dto.ItemDTO;
import com.switchfully.eurder.api.mappers.OrderMapper;
import com.switchfully.eurder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public ItemDTO save(UUID userId, CreateOrderDTO createOrderDTO) {
        return null;
    }
}
