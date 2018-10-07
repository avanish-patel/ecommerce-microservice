package com.solstice.orderorderlineservice.service;

import com.solstice.orderorderlineservice.domain.*;
import com.solstice.orderorderlineservice.feignClient.AddressClient;
import com.solstice.orderorderlineservice.feignClient.ProductClient;
import com.solstice.orderorderlineservice.feignClient.ShipmentClient;
import com.solstice.orderorderlineservice.repository.OrderLineRepository;
import com.solstice.orderorderlineservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderLineRepository orderLineRepository;

    @Autowired
    private AddressClient addressClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ShipmentClient shipmentClient;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public Iterable<Orders> getAllOrders() {

        return orderRepository.findAll();
    }

    public Optional<Orders> getOrder(long id) {

        return orderRepository.findById(id);
    }

    public Orders addOrder(Orders orders) {

        // generate shipment and add automatically
        Shipment shipment = new Shipment(0,orders.getAccountId(),orders.getShippingAddressId(),orderLineRepository.findLastOrderLIneId()+10, orders.getOrderDate().plusDays(1),orders.getOrderDate().plusDays(3));
        shipmentClient.addShipment(shipment);

        return orderRepository.save(orders);
    }

    public Orders updateOrder(long id, Orders orders) {
        return orderRepository.save(orders);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public Iterable<Orders> getOrdersByAccount(long accountId) {
        return orderRepository.findAllByAccountIdOrderByOrderDate(accountId);
    }

    public OrderLine addOrderLineItems(long id, OrderLine orderLine) {

        Orders orders = orderRepository.findById(id).get();

        // setting price of product from product table
        orderLine.setPrice(productClient.getProductById(orderLine.getProductId()).getPrice());

        orderLine.setOrders(orders);
        orderLine.setShipmentId(shipmentClient.getLastShipmentId());

        return orderLineRepository.save(orderLine);
    }

    public OrdersAggregation getOrdersAggregationByAccount(long orderId) {

        Orders orders = orderRepository.findById(orderId).get();
        Address address = addressClient.getAddressById(orders.getShippingAddressId());
        Set<OrderLine> orderLines = orders.getOrderLineSet();
        List<OrderLineItems> orderLineItems = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {

            long productId = orderLine.getProductId();
            Product product = productClient.getProductById(productId);

            String productName = product.getName();
            int quantity = orderLine.getQuantity();

            long shipmentId = orderLine.getShipmentId();
            Shipment shipment = shipmentClient.getShipment(shipmentId);
            orderLineItems.add(new OrderLineItems(productName, quantity, shipment.getShippingDate(), shipment.getDeliveryDate()));

        }


        return new OrdersAggregation(orders.getOrdersId(), address, orders.getTotal(), orderLineItems);
    }
}
