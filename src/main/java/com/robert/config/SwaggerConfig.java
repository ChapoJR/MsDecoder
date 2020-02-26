package com.robert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Roberto Armenta 
 * This class creates a the Configuration Bean of Swagger
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This method sets the the configuration and the APIs that will be deployed
     * in the Swagger UI
     *
     * @return Docket Internal Swagger class to create de UI to test all of my
     * API
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build().useDefaultResponseMessages(false);
    }
}
