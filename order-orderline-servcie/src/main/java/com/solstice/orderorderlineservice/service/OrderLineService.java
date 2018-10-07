package com.solstice.orderorderlineservice.service;

import com.solstice.orderorderlineservice.domain.OrderLine;
import com.solstice.orderorderlineservice.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineService {

    private OrderLineRepository orderLineRepository;

    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }


    public Iterable<OrderLine> getAllOrderLine() {

        return orderLineRepository.findAll();
    }

    public Optional<OrderLine> getOrderLine(long id){

        return orderLineRepository.findById(id);
    }

    public OrderLine addOrderLine(OrderLine orderLine) {

        return orderLineRepository.save(orderLine);

    }

    public OrderLine updateOrderLine(long id, OrderLine orderLine) {

       return orderLineRepository.save(orderLine);

    }

    public void deleteOrderLine(long id) {
        orderLineRepository.deleteById(id);
    }

    public long getOrderIdByOrderLineId(long orderLineId) {
        return orderLineRepository.getOrderIdByOrderLineId(orderLineId);
    }
}
