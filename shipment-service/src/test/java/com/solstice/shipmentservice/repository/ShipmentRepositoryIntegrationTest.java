package com.solstice.shipmentservice.repository;

import com.solstice.shipmentservice.domain.Shipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShipmentRepositoryIntegrationTest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    public void findAllByAccountIdOrderByDeliveryDate() {

        List<Shipment> shipments = (List<Shipment>) shipmentRepository.findAllByAccountIdOrderByDeliveryDate(1);

        assertThat(shipments.get(0).getDeliveryDate(), is(equalTo(LocalDate.of(2018, 06, 24))));
        assertThat(shipments.get(1).getShippingDate(), is(equalTo(LocalDate.of(2018, 06, 25))));
        assertThat(shipments.get(2).getDeliveryDate(), is(equalTo(LocalDate.of(2018, 07, 01))));
    }

    @Test
    public void findLastId() {

        long lastId = shipmentRepository.findLastId();

        assertNotNull(lastId);
    }
}