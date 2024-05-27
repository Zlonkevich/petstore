package com.salmon.common.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.springframework.stereotype.Service;

import static io.restassured.mapper.ObjectMapperType.JACKSON_2;

@Service
public class RestClientConfig {
    public static void init() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setBaseUri(SalmonProjectConfig.getJsonplaceholderBaseUrl())
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

        RestAssured.filters(
            ResponseLoggingFilter.responseLogger()
        );

        ObjectMapperConfig mapperConfig = new ObjectMapperConfig(JACKSON_2).jackson2ObjectMapperFactory((cls, charset) -> JacksonModule.MAPPER);

        RestAssured.defaultParser = Parser.JSON;

        RestAssured.config = new RestAssuredConfig()
            .decoderConfig(new DecoderConfig("UTF-8"))
            .encoderConfig(new EncoderConfig("UTF-8", "UTF-8"))
            .objectMapperConfig(mapperConfig);
    }
}
