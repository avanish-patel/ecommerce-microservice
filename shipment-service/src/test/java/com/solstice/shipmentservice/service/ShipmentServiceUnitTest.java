package com.solstice.shipmentservice.service;

import com.solstice.shipmentservice.domain.Shipment;
import com.solstice.shipmentservice.domain.ShipmentAggregation;
import com.solstice.shipmentservice.repository.ShipmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ShipmentServiceUnitTest {


    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllShipments() {

        List<Shipment> shipments = Arrays.asList(
                new Shipment(1,1,1, LocalDate.of(2018,6,22),LocalDate.of(2018,6,24)),
                new Shipment(2,2,2, LocalDate.of(2018,6,26),LocalDate.of(2018,6,28))

        );

        when(shipmentRepository.saveAll(shipments)).thenReturn(shipments);

        assertThat(shipments.get(0).getDeliveryDate(),is(equalTo(LocalDate.of(2018,6,24))));
        assertThat(shipments.get(0).getAccountId(),is(equalTo(1L)));
        assertThat(shipments.get(1).getShippingDate(),is(equalTo(LocalDate.of(2018,6,26))));
    }

    @Test
    public void getShipment() {

        Shipment shipment = new Shipment(1, 1, 1, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(shipment));

        assertThat(shipment.getShippingDate(), is(equalTo(LocalDate.of(2018, 6, 22))));
        assertThat(shipment.getDeliveryDate(), is(equalTo(LocalDate.of(2018, 6, 24))));
        assertThat(shipment.getOrderLineId(), is(equalTo(1L)));

    }

    @Test
    public void addShipment() {
        Shipment shipment = new Shipment(1, 1, 1, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        assertThat(shipment.getShippingDate(), is(equalTo(LocalDate.of(2018, 6, 22))));
        assertThat(shipment.getDeliveryDate(), is(equalTo(LocalDate.of(2018, 6, 24))));
        assertThat(shipment.getOrderLineId(), is(equalTo(1L)));

    }

    @Test
    public void updateShipment() {

        Shipment shipment = new Shipment(1, 1, 1, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        assertThat(shipment.getShippingDate(), is(equalTo(LocalDate.of(2018, 6, 22))));
        assertThat(shipment.getDeliveryDate(), is(equalTo(LocalDate.of(2018, 6, 24))));
        assertThat(shipment.getAccountId(), is(equalTo(1L)));

    }

    @Test
    public void deleteShipment() {


    }

    @Test
    public void getShipmentsByAccount() {

        List<ShipmentAggregation> shipmentAggregations = Arrays.asList(
                new ShipmentAggregation(1, LocalDate.of(2018, 06, 25), LocalDate.of(2018, 06, 27),"Iphone Xs", 4)
        );

        when(shipmentService.getShipmentsByAccount(any(Long.class))).thenReturn(shipmentAggregations);

        assertThat(shipmentAggregations.get(0).getOrderId(), is(equalTo(1L)));
        assertThat(shipmentAggregations.get(0).getShipemntDate(), is(equalTo(LocalDate.of(2018, 06, 25))));


    }
}