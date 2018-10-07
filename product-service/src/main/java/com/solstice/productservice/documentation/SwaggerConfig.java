package com.solstice.productservice.documentation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.solstice.productservice")).paths(PathSelectors.regex("/products.*"))
                .build().apiInfo(apiInfo());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Product Service REST API",
                "A​ ​RESTful​ ​API​ ​that​ ​would​ ​allow​ ​a​ ​web​ ​or​ ​mobile​ ​front-end​ ​to:\n"
                        + "● Create​ ​a​ Product ​record\n" + "● Retrieve​ ​a​ Product ​record by Product ID\n"
                        + "● Update​ ​a​ Product ​record\n" + "● Delete​ ​a​ Product ​record\n"
                        + "● Search​ ​for​ ​a​ Product ​by​ account ID\n",
                "API TOS", "Terms of service",
                new springfox.documentation.service.Contact("Avanishbhai Patel", "", "apatel@solstice.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
