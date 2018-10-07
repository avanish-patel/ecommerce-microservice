package com.solstice.shipmentservice.repository;

import com.solstice.shipmentservice.domain.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

    Iterable<Shipment> findAllByAccountIdOrderByDeliveryDate(long id);

    @Query(value = "select max(shipment_id) from shipment",nativeQuery = true)
    long findLastId();

}
