package com.typicode.jsonPlaceholder.api.posts;

import com.typicode.jsonPlaceholder.api.JsonPlaceholderUrlsEnum;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostsController {

    ValidatableResponse get(String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then()
            .log().body();
    }

    ValidatableResponse get(String postNumber, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postNumber)
            .then()
            .log().body();
    }

    ValidatableResponse getComments(String postNumber, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postNumber + "/" + "comments")
            .then();
    }


    ValidatableResponse post(String postNumber, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then();
    }

    ValidatableResponse put(String postNumber, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then();
    }

    ValidatableResponse patch(String postNumber, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then();
    }

    ValidatableResponse delete(String postNumber, String uuid) {
        return RestAssured.given()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then();
    }
}
