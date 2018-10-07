package com.solstice.orderorderlineservice.repository;

import com.solstice.orderorderlineservice.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    Iterable<Orders> findAllByAccountIdOrderByOrderDate(long accountId);
}
