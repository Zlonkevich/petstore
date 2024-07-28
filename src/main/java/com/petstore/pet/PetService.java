package com.petstore.pet;

import com.petstore.pet.post.PostResponse200sRec;
import com.petstore.pet.post.RequestData;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class PetService {
    @Autowired
    private PetController petController;

    @Step("Invoking 'POST /pet', waiting for the code 200s")
    public PostResponse200sRec postPet200s(RequestData post, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: '" + code + "' out of the range '200 <= code < 300'");

        return petController.post(post, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostResponse200sRec.class);
    }

    @Step("Invoking 'POST /pet', waiting for the code 400s")
    public PostResponse200sRec postPet400s(RequestData post, int code, String uuid) {
        assertTrue(400 <= code && code < 500, "Sent code: '" + code + "' out of the range '400 <= code < 500'");

        return petController.post(post, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostResponse200sRec.class);
    }
}
