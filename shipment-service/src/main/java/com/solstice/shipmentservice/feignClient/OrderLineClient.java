package com.solstice.shipmentservice.feignClient;

import com.solstice.shipmentservice.domain.OrderLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "order-orderline-service",url = "http://order-orderline-service-excellent-duiker.cfapps.io")
public interface OrderLineClient {

    @RequestMapping(path = "/orderlines/{id}", method = RequestMethod.GET)
    OrderLine getOrderLine(@PathVariable("id") long id);

    @RequestMapping(path = "orderlines/{id}/order", method = RequestMethod.GET)
    long getOrderIdByOrderLineId(@PathVariable("id") long id);
}
