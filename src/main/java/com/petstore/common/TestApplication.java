package com.petstore.common;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com"})
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TestApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
