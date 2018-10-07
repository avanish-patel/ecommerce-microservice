package com.solstice.accountaddressservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.accountaddressservice.domain.Address;
import com.solstice.accountaddressservice.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private AddressService addressService;
    private static Logger logger = LoggerFactory.getLogger(AddressController.class);

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    @HystrixCommand(fallbackMethod = "getAllAddressesFallback")
    public Iterable<Address> getAllAddresses() {

        return addressService.getAllAddresses();
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "addAddressFallback")
    public Address addAddress(@RequestBody Address address) {


        logger.info("Address is added :"+ address.toString());
        return addressService.addAddress(address);
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getAddressByIdFallback")
    public Address getAddressById(@PathVariable("id") long id) {

        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateAddressFallback")
    public Address updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
        logger.info("Address is updated :"+ address.toString());
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteAddressByIdFallback")
    public void deleteAddressById(@PathVariable("id") long id) {

        addressService.deleteAddressById(id);
        logger.info("Address is deleted.");
    }

    public Iterable<Address> getAllAddressesFallback() {
        logger.error("Fallback while getting list of addresses");
        return (Iterable<Address>) new Address();
    }

    public Address addAddressFallback(Address address) {
        logger.error("Fallback while adding address.");
        return new Address();
    }

    public Address getAddressByIdFallback(long id) {
        logger.error("Fallback while getting address by id");
        return new Address();
    }

    public Address updateAddressFallback(long id, Address address) {
        logger.error("Fallback while updating address");
        return new Address();
    }

    public void deleteAddressByIdFallback(long id) {
        logger.error("Fallback while deleting address.");
    }

}
