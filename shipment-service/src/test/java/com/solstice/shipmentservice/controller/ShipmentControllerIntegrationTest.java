package com.solstice.shipmentservice.controller;

import com.solstice.shipmentservice.domain.Shipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ShipmentControllerIntegrationTest {

    @Autowired
    private ShipmentController shipmentController;


    @Test
    public void getAllShipments() {

        List<Shipment> shipments = (List<Shipment>) shipmentController.getAllShipments();

        assertNotNull(shipments);
        assertEquals(LocalDate.of(2018,06,24),shipments.get(0).getDeliveryDate());
        assertEquals(11,shipments.get(1).getOrderLineId());

    }

    @Test
    public void getShipment() {

        Shipment shipment = shipmentController.getShipment(21);

        assertEquals(1, shipment.getAccountId());
        assertEquals(LocalDate.of(2018,07,01),shipment.getDeliveryDate());
    }

    @Test
    @Transactional
    @Rollback
    public void addShipment() {

        Shipment shipment = new Shipment(2, 2, 2, LocalDate.of(2018, 6, 26), LocalDate.of(2018, 6, 28));

        Shipment addedShipment = shipmentController.addShipment(shipment);

        assertEquals(2, addedShipment.getAccountId());
        assertEquals(2, addedShipment.getOrderLineId());
        assertEquals(LocalDate.of(2018,6,26),addedShipment.getShippingDate());
    }

    @Test
    public void updateShipment() {

    }

    @Test
    public void deleteShipment() {

        Shipment shipment = shipmentController.deleteShipment(161);

        assertEquals(LocalDate.of(2018, 06, 28), shipment.getDeliveryDate());
        assertEquals(2, shipment.getOrderLineId());
    }

    @Test
    public void getShipmentsByAccount() {
    }
}