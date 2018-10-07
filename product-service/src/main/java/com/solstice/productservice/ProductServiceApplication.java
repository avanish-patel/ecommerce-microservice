package com.solstice.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ProductServiceApplication {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

        SpringApplication.run(ProductServiceApplication.class, args);

        logger.info("Product service Up and Running...");
    }
}
