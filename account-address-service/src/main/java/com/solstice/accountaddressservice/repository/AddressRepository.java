package com.solstice.accountaddressservice.repository;

import com.solstice.accountaddressservice.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
