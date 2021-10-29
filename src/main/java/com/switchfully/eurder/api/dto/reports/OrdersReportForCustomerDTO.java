package com.switchfully.eurder.api.dto.reports;

import java.math.BigDecimal;
import java.util.List;

public class OrdersReportForCustomerDTO {

    private List<OrderReportDTO> orderReportDTOList;

    private final BigDecimal totalOfAllOrders;

    public OrdersReportForCustomerDTO(List<OrderReportDTO> orderReportDTOList, BigDecimal totalOfAllOrders) {
        this.orderReportDTOList = orderReportDTOList;
        this.totalOfAllOrders = totalOfAllOrders;
    }

    public List<OrderReportDTO> getOrderReportDTOList() {
        return orderReportDTOList;
    }

    public BigDecimal getTotalOfAllOrders() {
        return totalOfAllOrders;
    }

    public void addOrderReportDTO(OrderReportDTO orderReportDTO) {
        orderReportDTOList.add(orderReportDTO);
    }
}
