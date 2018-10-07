package com.solstice.shipmentservice.controller;

import com.solstice.shipmentservice.domain.Shipment;
import com.solstice.shipmentservice.domain.ShipmentAggregation;
import com.solstice.shipmentservice.service.ShipmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipmentController.class)
public class ShipmentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;


    @InjectMocks
    private ShipmentController shipmentController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllShipments() throws Exception {

        List<Shipment> shipments = Arrays.asList(
                new Shipment(1,1,1, LocalDate.of(2018,6,22),LocalDate.of(2018,6,24)),
                new Shipment(2,2,2, LocalDate.of(2018,6,26),LocalDate.of(2018,6,28))

        );

        when(shipmentService.getAllShipments()).thenReturn(shipments);

        mockMvc.perform(get("/shipments"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].orderLineId", is(1)))
                .andExpect(jsonPath("$[1].shippingDate", is("2018-06-26")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getShipment() throws Exception {

        Shipment shipment = new Shipment(2, 2, 2, LocalDate.of(2018, 6, 26), LocalDate.of(2018, 6, 28));

        when(shipmentService.getShipment(any(Long.class))).thenReturn(shipment);

        mockMvc.perform(get("/shipments/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shippingAddressId", is(2)))
                .andExpect(jsonPath("$.deliveryDate", is("2018-06-28")))
                .andReturn();
    }

    @Test
    public void addShipment() throws Exception {

        //language=JSON
        String json = "{\n" +
                "  \"accountId\":2,\n" +
                "  \"shippingAddressId\":2,\n" +
                "  \"orderLineId\":2,\n" +
                "  \"shippingDate\":\"2018-06-22\",\n" +
                "  \"deliveryDate\":\"2018-06-24\"\n" +
                "}";

        Shipment shipment = new Shipment(2, 2, 2, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentService.addShipment(any(Shipment.class))).thenReturn(shipment);

        mockMvc.perform(post("/shipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryDate", is("2018-06-24")))
                .andReturn();
    }

    @Test
    public void updateShipment() throws Exception {

        String json = "{\n" +
                "  \"accountId\":2,\n" +
                "  \"shippingAddressId\":2,\n" +
                "  \"orderLineId\":2,\n" +
                "  \"shippingDate\":\"2018-06-22\",\n" +
                "  \"deliveryDate\":\"2018-06-24\"\n" +
                "}";

        Shipment shipment = new Shipment(2, 2, 2, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentService.updateShipment(any(Long.class),any(Shipment.class))).thenReturn(shipment);

        mockMvc.perform(put("/shipments/{id}", 2).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shippingDate",is("2018-06-22")))
                .andReturn();

    }

    @Test
    public void deleteShipment() throws Exception {

        String json = "{\n" +
                "  \"accountId\":2,\n" +
                "  \"shippingAddressId\":2,\n" +
                "  \"orderLineId\":2,\n" +
                "  \"shippingDate\":\"2018-06-22\",\n" +
                "  \"deliveryDate\":\"2018-06-24\"\n" +
                "}";

        Shipment shipment = new Shipment(2, 2, 2, LocalDate.of(2018, 6, 22), LocalDate.of(2018, 6, 24));

        when(shipmentService.deleteShipment(any(Long.class))).thenReturn(shipment);

        mockMvc.perform(delete("/shipments/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", is(2)));

    }

    @Test
    public void getShipmentsByAccount() throws Exception {

        List<ShipmentAggregation> shipmentAggregations = Arrays.asList(
                new ShipmentAggregation(1, LocalDate.of(2018, 06, 25), LocalDate.of(2018, 06, 27),"Iphone Xs", 4)
        );

        when(shipmentService.getShipmentsByAccount(any(Long.class))).thenReturn(shipmentAggregations);

        mockMvc.perform(get("/shipments/account/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName",is("Iphone Xs")))
                .andReturn();

    }
}