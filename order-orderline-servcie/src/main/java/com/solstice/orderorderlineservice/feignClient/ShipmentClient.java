package com.solstice.orderorderlineservice.feignClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.orderorderlineservice.domain.Shipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "shipment-service", url = "http://shipment-service-zany-emu.cfapps.io")
public interface ShipmentClient {

    @RequestMapping(path = "/shipments/{id}", method = RequestMethod.GET)
    Shipment getShipment(@PathVariable("id") long id);

    @RequestMapping("/shipments/shipmentId")
    long getLastShipmentId();

    @RequestMapping(path = "/shipments", method = RequestMethod.POST)
    Shipment addShipment(@RequestBody Shipment shipment);

}
