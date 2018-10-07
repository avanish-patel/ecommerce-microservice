package com.solstice.orderorderlineservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.solstice.orderorderlineservice.OrderOrderlineServiceApplication;
import com.solstice.orderorderlineservice.domain.*;
import com.solstice.orderorderlineservice.feignClient.AccountClient;
import com.solstice.orderorderlineservice.feignClient.ProductClient;
import com.solstice.orderorderlineservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private ProductClient productClient;


    private static Logger logger = LoggerFactory.getLogger(OrderOrderlineServiceApplication.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    @HystrixCommand(fallbackMethod = "getAllOrdersFallback")
    public Iterable<Orders> getAllOrders(){

        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getOrderFallback")
    public Optional<Orders> getOrder(@PathVariable("id") long id){
        return orderService.getOrder(id);
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "addOrderFallback")
    public Orders addOrder(@RequestBody Orders orders) {

        // call account service and check for account
        Account account = accountClient.getAccount(orders.getAccountId());

        if (account != null) {
            logger.info("Order added :"+ orders.toString());
            return orderService.addOrder(orders);

        }else {
            logger.error("Account doesn't exist.");
            return new Orders();
        }
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateOrderFallback")
    public Orders updateOrder(@PathVariable("id")long id,@RequestBody Orders orders){

        logger.info("Order updated :"+ orders.toString());
        return orderService.updateOrder(id,orders);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteOrderLineFallback")
    public void deleteOrderLine(@PathVariable("id")long id){
        orderService.deleteOrder(id);
        logger.info("Order deleted.");

    }

    @GetMapping("/account/{id}")
    @HystrixCommand(fallbackMethod = "getOrdersByAccountFallback")
    public Iterable<Orders> getOrdersByAccount(@PathVariable("id") long accountId) {
        return orderService.getOrdersByAccount(accountId);
    }

    @PostMapping("/{id}/orderline")
    @HystrixCommand(fallbackMethod = "addOrderLineItemsFallback")
    public OrderLine addOrderLineItems(@PathVariable("id") long id, @RequestBody OrderLine orderLine) {

        Product product = productClient.getProductById(orderLine.getProductId());

        if (product != null) {

            return orderService.addOrderLineItems(id, orderLine);
        }else {
            logger.error("Product with id "+ orderLine.getProductId()+ " doesn't exist");
            return new OrderLine();
        }
    }

    @GetMapping("/{id}/agg")
    @HystrixCommand(fallbackMethod = "getOrdersAggregationByAccountFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public OrdersAggregation getOrdersAggregationByAccount(@PathVariable("id") long orderId) {
        return orderService.getOrdersAggregationByAccount(orderId);
    }

    public Iterable<Orders> getAllOrdersFallback() {
        logger.error("Fallback while getting list of orders");
        return (Iterable<Orders>) new Orders();
    }

    public Optional<Orders> getOrderFallback(long id) {
        logger.error("Fallback while getting order by ID " + id);
        return Optional.of(new Orders());
    }

    public Orders addOrderFallback(Orders orders){
        logger.error("Fallback while adding order.");
        return new Orders();
    }

    public Orders updateOrderFallback(long id, Orders orders) {
        logger.error("Fallback while updating order");
        return new Orders();
    }

    public void deleteOrderLineFallback(long id) {
        logger.error("Fallback while deleting order.");

    }

    public Iterable<Orders> getOrdersByAccountFallback(long accountId) {
        logger.error("Fallback while getting order by Account.");
        return (Iterable<Orders>) new Orders();
    }

    public OrderLine addOrderLineItemsFallback(long id, OrderLine orderLine) {
        logger.error("Fallback while adding orderline items to order.");
        return new OrderLine();
    }

    public OrdersAggregation getOrdersAggregationByAccountFallback(long orderId) {
        logger.error("Fallback while getting aggregated order.");
        return new OrdersAggregation();
    }



}
