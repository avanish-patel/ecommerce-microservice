package com.solstice.orderorderlineservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.orderorderlineservice.domain.OrderLine;
import com.solstice.orderorderlineservice.service.OrderLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orderlines")
public class OrderLineController {

    private OrderLineService orderLineService;
    private static Logger logger = LoggerFactory.getLogger(OrderLineController.class);

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;

    }

    @GetMapping()
    @HystrixCommand(fallbackMethod = "getAllOrderLineFallback")
    public Iterable<OrderLine> getAllOrderLine(){

        return orderLineService.getAllOrderLine();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getOrderLineFallback")
    public Optional<OrderLine> getOrderLine(@PathVariable("id") long id){
        return orderLineService.getOrderLine(id);
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "addOrderLineFallback")
    public OrderLine addOrderLine(@RequestBody OrderLine orderLine) {

        logger.info("OrderLine added :"+ orderLine.toString());
        return orderLineService.addOrderLine(orderLine);
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateOrderLineFallback")
    public OrderLine updateOrderLine(@PathVariable("id")long id,@RequestBody OrderLine orderLine){

        logger.info("OrderLine updated :"+ orderLine.toString());
       return orderLineService.updateOrderLine(id,orderLine);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteOrderLineFallback")
    public void deleteOrderLine(@PathVariable("id")long id){
        orderLineService.deleteOrderLine(id);
        logger.info("OrderLine deleted.");
    }

    @GetMapping("/{id}/order")
    @HystrixCommand(fallbackMethod = "getOrderIdByOrderLineIdFallback")
    public long getOrderIdByOrderLineId(@PathVariable("id") long orderLineId) {

        return orderLineService.getOrderIdByOrderLineId(orderLineId);
    }

    public Iterable<OrderLine> getAllOrderLineFallback() {
        logger.error("Fallback while getting list of Orderline.");
        return (Iterable<OrderLine>) new OrderLine();
    }

    public Optional<OrderLine> getOrderLineFallback(long id) {
        logger.error("Fallback while getting Orderline.");
        return Optional.of(new OrderLine());
    }

    public OrderLine addOrderLineFallback(OrderLine orderLine) {
        logger.error("Fallback while adding orderline");
        return new OrderLine();
    }

    public OrderLine updateOrderLineFallback(long id, OrderLine orderLine) {
        logger.error("Fallback while updating Orderline.");
        return new OrderLine();
    }

    public void deleteOrderLineFallback(long id) {
        logger.error("Fallback while deleting Orderline");

    }

    public long getOrderIdByOrderLineIdFallback(long orderLineId) {
        logger.error("Fallback while getting order Id from orderline.");
        return 0L;
    }

}
