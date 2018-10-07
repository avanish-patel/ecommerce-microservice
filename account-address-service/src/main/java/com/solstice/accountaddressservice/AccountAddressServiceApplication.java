package com.solstice.accountaddressservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class AccountAddressServiceApplication {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(AccountAddressServiceApplication.class);

        SpringApplication.run(AccountAddressServiceApplication.class, args);

        logger.info("Account-Address Service Up and Running...");
    }
}
