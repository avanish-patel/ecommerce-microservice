package com.solstice.shipmentservice.service;

import com.solstice.shipmentservice.domain.Shipment;
import com.solstice.shipmentservice.domain.ShipmentAggregation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class ShipmentServiceIntegrationTest {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    public void getAllShipments() {

        Iterable<Shipment> shipments = shipmentService.getAllShipments();

        assertNotNull(shipments);
    }

    @Test
    public void getShipment() {

        Shipment shipment = shipmentService.getShipment(1);

        assertEquals(1, shipment.getOrderLineId());
        assertEquals(LocalDate.of(2018, 6, 23), shipment.getDeliveryDate());
        assertEquals(LocalDate.of(2018,6,21), shipment.getShippingDate());
    }

    @Test
    public void addShipment() {

        Shipment shipment = new Shipment();
        shipment.setAccountId(2);
        shipment.setShippingDate(LocalDate.of(2018, 06, 22));
        shipment.setDeliveryDate(LocalDate.of(2018, 06, 25));
        shipment.setOrderLineId(2);
        shipment.setShippingAddressId(2);


        Shipment addedShipment = shipmentService.addShipment(shipment);

        assertEquals(shipment.getAccountId(), addedShipment.getAccountId());
        assertEquals(shipment.getDeliveryDate(), addedShipment.getDeliveryDate());
        assertNotNull(addedShipment);
    }

    @Test
    public void updateShipment() {

        Shipment shipment = new Shipment();
        shipment.setAccountId(2);
        shipment.setShippingDate(LocalDate.of(2018, 06, 22));
        shipment.setDeliveryDate(LocalDate.of(2018, 06, 25));
        shipment.setOrderLineId(2);
        shipment.setShippingAddressId(2);

        Shipment updatedShipment = shipmentService.updateShipment(2, shipment);

        assertNotNull(updatedShipment);
        assertEquals(shipment.getDeliveryDate(), updatedShipment.getDeliveryDate());
        assertEquals(shipment.getShippingDate(), updatedShipment.getShippingDate());

    }

    @Test
    public void deleteShipment() {

        Shipment shipment = shipmentService.deleteShipment(1);

        assertNotNull(shipment);
        assertEquals(LocalDate.of(2018, 06, 23), shipment.getDeliveryDate());
        assertEquals(1, shipment.getOrderLineId());
    }

    @Test
    public void getShipmentsByAccount() {

        List<ShipmentAggregation> shipmentAggregations = Arrays.asList(
                new ShipmentAggregation(1, LocalDate.of(2018, 06, 21), LocalDate.of(2018, 06, 23), "Iphone XS", 3),
                new ShipmentAggregation(1, LocalDate.of(2018, 06, 22), LocalDate.of(2018, 06, 24), "Iphone XS", 3),
                new ShipmentAggregation(1, LocalDate.of(2018, 06, 24), LocalDate.of(2018, 06, 24), "Iphone XS", 3)

        );

        List<ShipmentAggregation> fetchedAggregation = shipmentService.getShipmentsByAccount(1L);

        assertNotNull(shipmentAggregations.indexOf(0));
        assertEquals(shipmentAggregations.size(), fetchedAggregation.size());
        assertEquals(shipmentAggregations.get(0).getDeliveryDate(), shipmentAggregations.get(0).getDeliveryDate());
    }
}