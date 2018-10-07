package com.solstice.productservice.controller;

import com.solstice.productservice.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback()
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductController productController;

    @Test
    public void getProducts() {

        List<Product> products = (List<Product>) productController.getProducts();

        assertNotNull(products);
        assertEquals("Note 9", products.get(0).getName());
        assertEquals("Iphone Xs", products.get(1).getDescription());
        assertEquals("Iphone Xr", products.get(2).getName());
        assertEquals(999.99, products.get(2).getPrice(),0);

    }

    @Test
    public void getProductById() {

        Product product = productController.getProductById(3);

        assertNotNull(product);
        assertEquals("Iphone Xr", product.getName());
        assertEquals(999.99, product.getPrice(), 0);
    }

    @Test
    public void addProduct() {

        Product product = new Product("OnePlus 6", "OnePlus 6", null, 499.99);

        Product addedProduct = productController.addProduct(product);

        assertThat("OnePlus 6", is(equalTo(addedProduct.getName())));
        assertThat(499.99, is(equalTo(addedProduct.getPrice())));
        assertThat("OnePlus 6",is(equalTo(addedProduct.getDescription())));

    }

    @Test
    public void updateProduct() {

        Product product = new Product("Pocophone", "Cheapest flagship", null, 299.99);

        Product updatedProduct = productController.updateProduct(1, product);

        assertThat("Pocophone", is(equalTo(updatedProduct.getName())));
        assertThat("Cheapest flagship", is(equalTo(updatedProduct.getDescription())));
        assertThat(299.99, is(equalTo(updatedProduct.getPrice())));
    }

    @Test
    @Transactional
    @Rollback
    public void removeProduct() {

        Product removedProduct = productController.removeProduct(10);

        assertThat(removedProduct.getName(), is(equalTo("OnePlus 6")));
        assertThat(removedProduct.getPrice(),is(equalTo(499.99)));
        assertThat(removedProduct.getDescription(),is(equalTo("OnePlus 6")));
    }
}