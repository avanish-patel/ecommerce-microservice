package com.solstice.productservice.service;

import com.solstice.productservice.domain.Product;
import com.solstice.productservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts() {

        List<Product> products = Arrays.asList(
                new Product("Iphone X", "Iphone X", null, 999.99),
                new Product("Iphone Xs", "Iphone Xs", null, 999.99),
                new Product("Iphone Xr", "Iphone Xr", null, 749.99)
        );

        when(productRepository.saveAll(products)).thenReturn(products);

        assertThat(products.get(0).getPrice(), is(equalTo(999.99)));
        assertThat(products.get(0).getName(), is(equalTo("Iphone X")));
        assertThat(products.get(1).getDescription(), is(equalTo("Iphone Xs")));
    }

    @Test
    public void getProductById() {

        Product product = new Product("Iphone XR", "Iphone XR", null, 749.99);

        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(product));

        assertThat(product.getDescription(), is(equalTo("Iphone XR")));
        assertThat(product.getPrice(), is(equalTo(749.99)));
    }

    @Test
    public void addProduct() {
        Product product = new Product("Iphone XR", "Iphone XR", null, 749.99);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertThat(product.getName(), is(equalTo("Iphone XR")));
        assertThat(product.getPrice(), is(equalTo(749.99)));
        assertThat(product.getName(), is(equalTo("Iphone XR")));
    }


    @Test
    public void updateProduct() {

        Product product = new Product("Iphone XR", "Iphone XR", null, 749.99);

        when(productRepository.save(product)).thenReturn(product);

        assertThat(product.getName(), is(equalTo("Iphone XR")));
        assertThat(product.getPrice(), is(equalTo(749.99)));
        assertThat(product.getName(), is(equalTo("Iphone XR")));
    }

//    @Test
//    public void removeProduct(){
//
//        Product product = new Product("Iphone XR", "Iphone XR", null, 749.99);
//
//        Mockito.doReturn(when(productRepository.deleteById(any(Long.class))).thenReturn(product));
//
//        Product deletedProduct = productService.removeProduct(product.getProduct_id());
//
//
//        assertThat(product.getName(), is(equalTo(null)));
//    }
}