package com.solstice.productservice.service;

import com.solstice.productservice.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class ProductServiceIntegrationTest {


    @Autowired
    private ProductService productService;

    @Test
    public void getProducts() {


        List<Product> productsfetch = (List<Product>) productService.getProducts();

        assertNotNull(productsfetch);
        assertEquals("Note 9", productsfetch.get(0).getName());
        assertEquals("Iphone Xr", productsfetch.get(2).getName());
        assertEquals(999.99, productsfetch.get(2).getPrice(),0);
    }


    @Test
    public void getProductById() {

        Product firstProduct = productService.getProductById(1L);

        assertNotNull(firstProduct);
        assertEquals("Note 9", firstProduct.getName());
        assertEquals("Note 9", firstProduct.getDescription());

    }

    @Test
    public void addProduct() {

        Product product = new Product("Iphone XR", "Iphone XR", null, 749.99);

        Product savedProduct = productService.addProduct(product);

        assertEquals(savedProduct.getName(), product.getName());
        assertNotNull(savedProduct);
        assertEquals(savedProduct.getPrice(), product.getPrice(), 0);
    }

    @Test
    public void removeProduct() {

        Product deletedProduct = productService.removeProduct(3);

        assertEquals(999.99,deletedProduct.getPrice(),0);
        assertEquals("Iphone Xr", deletedProduct.getName());
    }

    @Test
    public void updateProduct() {

        Product product = new Product("Iphone XR Max", "Iphone XR Max", null, 899.99);

        Product updatedProduct = productService.updateProduct(2,product);

        assertEquals("Iphone XR Max", updatedProduct.getName());
        assertEquals(899.99, updatedProduct.getPrice(), 0);
        assertNotNull(updatedProduct);
    }
}