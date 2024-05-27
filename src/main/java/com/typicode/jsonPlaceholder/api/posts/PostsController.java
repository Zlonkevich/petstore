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

    ValidatableResponse get(int postNumber, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postNumber)
            .then()
            .log().body();
    }

    ValidatableResponse getComments(int postId, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postId + "/" + "comments")
            .then()
            .log().body();
    }

    ValidatableResponse getComment(int postId, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .pathParam("postId", postId)
            .get(JsonPlaceholderUrlsEnum.COMMENTS.getUrl())
            .then()
            .log().body();
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
