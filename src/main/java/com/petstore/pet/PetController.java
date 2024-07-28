package com.petstore.pet;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PetController {

    ValidatableResponse post(Object request, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .body(request)
            .when()
            .post(PetUrlsEnum.PET.getUrl())
            .then();
    }
}
