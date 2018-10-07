package com.solstice.productservice.repository;

import com.solstice.productservice.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProducts() {

        List<Product> products = (List<Product>) productRepository.findAll();

        assertThat(products.get(0).getPrice(), is(equalTo(999.99)));
        assertThat(products.get(1).getName(), is(equalTo("Iphone Xs")));
    }

    @Test
    public void getProductById() {

        Product product = productRepository.findById(1L).get();

        assertThat(product.getName(), is(equalTo("Note 9")));
        assertThat(product.getPrice(), is(equalTo(999.99)));
    }

    @Test
    public void addProduct() {
        Product product = new Product("Ipad", "New Ipad 2018", null, 699.99);

        Product addedProduct = productRepository.save(product);

        assertThat(addedProduct.getName(), is(equalTo("Ipad")));
        assertThat(addedProduct.getPrice(), is(equalTo(699.99)));

    }

    @Test
    public void updateProduct() {

        Product product = new Product("Ipad", "New Ipad 2018", null, 699.99);

        Product addedProduct = productRepository.save(product);

        assertThat(addedProduct.getName(), is(equalTo("Ipad")));
        assertThat(addedProduct.getPrice(), is(equalTo(699.99)));
    }

    @Test
    public void removeProduct() {

        productRepository.deleteById(4L);

        Optional<Product> deletedProduct = productRepository.findById(5L);

        assertThat(deletedProduct, is(equalTo(Optional.empty())));

    }



}