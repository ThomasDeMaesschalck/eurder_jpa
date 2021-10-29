package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.reports.OrderReportDTO;
import com.switchfully.eurder.api.dto.reports.OrderlineReportDTO;
import com.switchfully.eurder.api.dto.reports.OrdersReportForCustomerDTO;
import com.switchfully.eurder.domain.entities.Orderline;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class ReportMapper {

    public OrderReportDTO toOrderReportDTO(UUID orderId, List<OrderlineReportDTO> orderlines) {
        return new OrderReportDTO(orderId, orderlines);
    }

    public OrderlineReportDTO toOrderlineReportDTO(Orderline orderline) {
        return new OrderlineReportDTO(orderline.getName(), orderline.getAmount(), orderline.getOrderlineTotal());
    }

    public OrdersReportForCustomerDTO toOrdersReportForCustomerDTO(List<OrderReportDTO> listOfAllOrdersFromCustomer, BigDecimal totalOfAllOrders) {
        return new OrdersReportForCustomerDTO(listOfAllOrdersFromCustomer, totalOfAllOrders);
    }
}
