package com.solstice.productservice.controller;

import com.solstice.productservice.domain.Product;
import com.solstice.productservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product("Iphone X", "Iphone X", null, 999.99),
                new Product("Iphone Xs", "Iphone Xs", null, 999.99),
                new Product("Iphone Xr", "Iphone Xr", null, 749.99)
        );

        when(productService.getProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name", is("Iphone X")))
                .andExpect(jsonPath("$[2].price", is(749.99)))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void getProductById() throws Exception {

        Product product = new Product("OnePlus 6", "OnePlus 6", null, 499.99);

        when(productService.getProductById(any(Long.class))).thenReturn(product);

        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name", is("OnePlus 6")))
                .andExpect(jsonPath("$.price", is(499.99)))
                .andReturn();
    }

    @Test
    public void addProduct() throws Exception {

        //language=JSON
        String json = "{\n" +
                "  \"name\":\"OnePlus 6\",\n" +
                "  \"description\":\"The new OnePlus 6\",\n" +
                "  \"image\":\"null\",\n" +
                "  \"price\":499.99\n" +
                "}";

        Product product = new Product("OnePlus 6", "The new OnePlus 6", null, 499.99);

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(
                post("/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("The new OnePlus 6")))
                .andExpect(jsonPath("$.price", is(499.99)))
                .andReturn();
    }

    @Test
    public void updateProduct() throws Exception {

        String json = "{\n" +
                "  \"name\":\"OnePlus 6\",\n" +
                "  \"description\":\"The new OnePlus 6\",\n" +
                "  \"image\":\"null\",\n" +
                "  \"price\":499.99\n" +
                "}";

        Product product = new Product("OnePlus 6", "The new OnePlus 6", null, 499.99);

        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/{id}",1).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("OnePlus 6")))
                .andReturn();

    }

    @Test
    public void removeProduct() throws Exception {

        String json = "{\n" +
                "  \"name\":\"OnePlus 6\",\n" +
                "  \"description\":\"The new OnePlus 6\",\n" +
                "  \"image\":\"null\",\n" +
                "  \"price\":499.99\n" +
                "}";

        Product product = new Product("OnePlus 6", "The new OnePlus 6", null, 499.99);

        when(productService.removeProduct(any(Long.class))).thenReturn(product);

        mockMvc.perform(delete("/products/{id}", 2).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(499.99)))
                .andReturn();

    }
}