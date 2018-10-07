package com.solstice.shipmentservice.service;

import com.solstice.shipmentservice.domain.OrderLine;
import com.solstice.shipmentservice.domain.Product;
import com.solstice.shipmentservice.domain.Shipment;
import com.solstice.shipmentservice.domain.ShipmentAggregation;
import com.solstice.shipmentservice.feignClient.OrderLineClient;
import com.solstice.shipmentservice.feignClient.ProductClient;
import com.solstice.shipmentservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderLineClient orderLineClient;
    @Autowired
    private ProductClient productClient;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Iterable<Shipment> getAllShipments(){
        return shipmentRepository.findAll();
    }

    public Shipment getShipment(long id){
        return shipmentRepository.findById(id).get();
    }

    public Shipment addShipment(Shipment shipment) {

        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(long id, Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment deleteShipment(long id) {
        Shipment shipment = shipmentRepository.findById(id).get();

        shipmentRepository.deleteById(id);
        return shipment;

    }

    public List<ShipmentAggregation> getShipmentsByAccount(long accountId) {

        List<Shipment> shipmentList = new ArrayList<>();
        List<ShipmentAggregation> shipmentAggregations = new ArrayList<>();

        shipmentRepository.findAllByAccountIdOrderByDeliveryDate(accountId).forEach(shipmentList::add);


        for (Shipment shipment: shipmentList ) {

            long orderLineId = shipment.getOrderLineId();
            OrderLine orderLine = orderLineClient.getOrderLine(orderLineId);
            long orderId = orderLineClient.getOrderIdByOrderLineId(orderLineId);
            Product product = productClient.getProductById(new Long(orderLine.getProductId()));

            shipmentAggregations.add(new ShipmentAggregation(orderId, shipment.getShippingDate(), shipment.getDeliveryDate(), product.getName(), orderLine.getQuantity()));

        }

        return shipmentAggregations;
    }

    public long getLastShipmentId() {
        return shipmentRepository.findLastId();
    }
}
