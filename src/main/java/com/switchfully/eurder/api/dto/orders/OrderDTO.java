package com.switchfully.eurder.api.dto.orders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OrderDTO {

    private final Long id;
    private final Long customerId;
    private final Set<OrderlineDTO> orderlineDTOSet;
    private final BigDecimal totalOrderPrice;

    public OrderDTO(Long id, Long customerId, BigDecimal totalOrderPrice) {
        orderlineDTOSet = new HashSet<>();
        this.id = id;
        this.customerId = customerId;
        this.totalOrderPrice = totalOrderPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Set<OrderlineDTO> getOrderlineDTOSet() {
        return orderlineDTOSet;
    }

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void addOrderlineDTO(OrderlineDTO orderlineDTO){
        orderlineDTOSet.add(orderlineDTO);
    }

}
