package com.solstice.shipmentservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.solstice.shipmentservice.domain.Shipment;
import com.solstice.shipmentservice.domain.ShipmentAggregation;
import com.solstice.shipmentservice.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private ShipmentService shipmentService;
    private static Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "getAllShipmentsFallBack")
    public Iterable<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getShipmentFallBack")
    public Shipment getShipment(@PathVariable("id") long id) {
        return shipmentService.getShipment(id);
    }

    @PostMapping
    @HystrixCommand(fallbackMethod = "addShipmentFallBack")
    public Shipment addShipment(@RequestBody Shipment shipment) {
        logger.info("Shipment added : " + shipment.toString());
        return shipmentService.addShipment(shipment);
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateShipmentFallBack")
    public Shipment updateShipment(@PathVariable("id") long id, @RequestBody Shipment shipment) {
        logger.info("Shipment updated : " + shipment.toString());

        return shipmentService.updateShipment(id, shipment);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteShipmentFallBack")
    public Shipment deleteShipment(@PathVariable("id") long id) {

        logger.info("Shipment deleted.");
        return shipmentService.deleteShipment(id);
    }


    @HystrixCommand(fallbackMethod = "getShipmentsByAccountFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @GetMapping("/account/{id}")
    public List<ShipmentAggregation> getShipmentsByAccount(@PathVariable("id") long accountId) {

        return shipmentService.getShipmentsByAccount(accountId);
    }

    @GetMapping("/shipmentId")
    public long getLastShipmentId(){
        return shipmentService.getLastShipmentId();
    }

    public Iterable<Shipment> getAllShipmentsFallBack() {
        logger.error("Fallback while getting list of shipemnts");
        return (Iterable<Shipment>) new Shipment();
    }

    public Shipment getShipmentFallBack(long id) {
        logger.error("Fallback while getting Shipment with ID " + id);
        return new Shipment();
    }

    public Shipment addShipmentFallBack(Shipment shipment) {
        logger.error("Fallback while adding shipment");
        return new Shipment();
    }

    public Shipment updateShipmentFallBack(long id, Shipment shipment) {
        logger.error("Fallback while updating shipment");
        return new Shipment();
    }

    public Shipment deleteShipmentFallBack(long id) {
        logger.info("Fallback while deleting Shipment");
        return new Shipment();

    }

    public List<ShipmentAggregation> getShipmentsByAccountFallback(long accountId) {

        List<ShipmentAggregation> shipmentList = new ArrayList<>();

        logger.error("Fallback while getting Shipment Aggregation");
        return shipmentList;
    }


}

