package com.petstore.pet;

import com.petstore.pet.post.RequestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PetController {

    ValidatableResponse postPet(RequestData request, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .body(request)
            .when()
            .post(PetUrlsEnum.PET.getUrl())
            .then();
    }

    ValidatableResponse postUploadImage(com.petstore.pet.petId.uploadImage.RequestData request, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .contentType(ContentType.MULTIPART)
            .body(request)
            .multiPart(request.getFile())
            .pathParam("petId", request.getPetId())
            .when()
            .post(PetUrlsEnum.UPLOAD_IMAGE.getUrl())
            .then();
    }

    ValidatableResponse putPet(RequestData request, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .body(request)
            .when()
            .put(PetUrlsEnum.PET.getUrl())
            .then();
    }
}
