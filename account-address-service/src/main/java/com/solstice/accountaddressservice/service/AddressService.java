package com.solstice.accountaddressservice.service;

import com.solstice.accountaddressservice.domain.Address;
import com.solstice.accountaddressservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressById(long id) {

        return addressRepository.findById(id).get();
    }

    public Iterable<Address> getAllAddresses() {

        return addressRepository.findAll();
    }

    public Address addAddress(Address address) {

        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address address) {

        return addressRepository.save(address);
    }

    public void deleteAddressById(long id) {

        addressRepository.deleteById(id);
    }


}
