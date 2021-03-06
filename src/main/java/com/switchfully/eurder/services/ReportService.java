package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.reports.OrderReportDTO;
import com.switchfully.eurder.api.dto.reports.OrderlineReportDTO;
import com.switchfully.eurder.api.dto.reports.OrdersReportForCustomerDTO;
import com.switchfully.eurder.api.mappers.ReportMapper;
import com.switchfully.eurder.domain.entities.Order;
import com.switchfully.eurder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(UserService userService, OrderRepository orderRepository, ReportMapper reportMapper) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.reportMapper = reportMapper;
    }

    public OrdersReportForCustomerDTO getReportOfOrdersForCustomer(Long userId, Long customerId) {
        assertIdOfRetrieverIsSameAsCustomerIdOfOrders(userId, customerId);
        userService.assertUserId(customerId);

        List<OrderReportDTO> listOfAllOrdersFromCustomer = new ArrayList<>();

        processorAllOrdersOfTheCustomer(getAllOrdersOfCustomer(customerId), listOfAllOrdersFromCustomer);

        return reportMapper.toOrdersReportForCustomerDTO(listOfAllOrdersFromCustomer, calculateTotalOfAllCustomerOrders(listOfAllOrdersFromCustomer));
    }

    private List<Order> getAllOrdersOfCustomer(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    private void processorAllOrdersOfTheCustomer(List<Order> orders, List<OrderReportDTO> listOfAllOrdersFromCustomer) {
        orders.forEach(order -> processIndividualOrder(order, listOfAllOrdersFromCustomer));
    }

    private void processIndividualOrder(Order order, List<OrderReportDTO> listOfAllOrdersFromCustomer) {
        listOfAllOrdersFromCustomer.add(reportMapper.toOrderReportDTO(order.getId(), processOrderlinesOfIndividualOrder(order)));
    }

    private List<OrderlineReportDTO> processOrderlinesOfIndividualOrder(Order order) {
        return order.getOrderlines().stream()
                .map(reportMapper::toOrderlineReportDTO)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTotalOfAllCustomerOrders(List<OrderReportDTO> orderReportDTOList) {
        return orderReportDTOList.stream()
                .map(OrderReportDTO::getOrderlineReportDTOList)
                .flatMap(List::stream)
                .map(OrderlineReportDTO::getOrderlineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void assertIdOfRetrieverIsSameAsCustomerIdOfOrders(Long userId, Long customerId) {
        if (!userId.equals(customerId)) {
            throw new IllegalArgumentException("You can't view report from another customer");
        }
    }

}
