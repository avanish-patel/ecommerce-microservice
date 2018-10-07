package com.solstice.orderorderlineservice.repository;

import com.solstice.orderorderlineservice.domain.OrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

    @Query(value = "select orders_orders_id from order_line o where o.order_line_id = ?1", nativeQuery = true)
    Long getOrderIdByOrderLineId(@Param("orderLineId") long id);

    @Query(value = "select max(order_line_id) from order_line",nativeQuery = true)
    long findLastOrderLIneId();

}
