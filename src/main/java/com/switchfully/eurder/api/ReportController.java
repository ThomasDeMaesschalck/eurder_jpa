package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.reports.OrdersReportForCustomerDTO;
import com.switchfully.eurder.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/reports")
public class ReportController {

    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrdersReportForCustomerDTO getAllOrdersFromUser(@RequestHeader(value = "userId") UUID userId, @PathVariable UUID customerId) {
        logger.info("User with id " + userId + " getting report for user with id " + customerId);
        return reportService.getReportOfOrdersForCustomer(userId, customerId);
    }
}
