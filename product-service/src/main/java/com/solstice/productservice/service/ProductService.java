package com.solstice.productservice.service;

import com.solstice.productservice.domain.Product;
import com.solstice.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).get();
    }

    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public Product removeProduct(long id) {

        Product product = productRepository.findById(id).get();

        productRepository.deleteById(id);
        return product;
    }

    public Product updateProduct(long id, Product product) {

        return productRepository.save(product);
    }
}
