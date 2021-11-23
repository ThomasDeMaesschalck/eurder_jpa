package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.orders.CreateOrderDTO;
import com.switchfully.eurder.api.dto.orders.OrderDTO;
import com.switchfully.eurder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestHeader(value = "userId") Long userId,
                               @RequestBody CreateOrderDTO createOrderDTO) {
        logger.info("User with id " + userId + " is making an order");
        return orderService.save(userId, createOrderDTO);
    }
}
