package com.solstice.productservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.productservice.domain.Product;
import com.solstice.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "getProductsFallBack")
    public Iterable<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getProductByIdFallBack")
    public Product getProductById(@PathVariable("id") long id) {

        return productService.getProductById(id);
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "addProductFallBack")
    public Product addProduct(@RequestBody Product product) {

        logger.info("Product added : "+ product.toString());
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateProductFallBack")
    public Product updateProduct(@PathVariable("id")long id, @RequestBody Product product) {
        logger.info("Product updated : "+ product.toString());

        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "removeProductFallBack")
    public Product removeProduct(@PathVariable("id") long id) {
        logger.info("Product deleted.");

        return productService.removeProduct(id);
    }

    public Product removeProductFallBack(long id){
        logger.error("Fallback while deleting Product");
        return new Product();
    }

    public Product getProductByIdFallBack(long id){

        logger.error("Fallback while getting product by ID"+ id);
        return new Product();
    }

    public Product addProductFallBack(Product product){

        logger.error("Fallback while adding the product.");
        return new Product();
    }

    public Product updateProductFallBack(long id, Product product) {
        logger.error("Fallback while updating the product");
        return new Product();
    }

    public Iterable<Product> getProductsFallBack() {

        logger.error("Fallback while getting list of product");
        return (Iterable<Product>) new Product();
    }
}
