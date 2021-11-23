package com.switchfully.eurder.api.dto.reports;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.UUID;

public class OrderReportDTO {

    private final Long orderId;
    private final List<OrderlineReportDTO> orderlineReportDTOList;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OrderReportDTO(Long orderId, List<OrderlineReportDTO> orderlineReportDTOList) {
        this.orderId = orderId;
        this.orderlineReportDTOList = orderlineReportDTOList;
    }

    public List<OrderlineReportDTO> getOrderlineReportDTOList() {
        return orderlineReportDTOList;
    }

    public Long getOrderId() {
        return orderId;
    }
}
