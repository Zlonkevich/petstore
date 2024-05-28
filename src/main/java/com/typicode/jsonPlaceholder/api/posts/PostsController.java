package com.typicode.jsonPlaceholder.api.posts;

import com.typicode.jsonPlaceholder.api.JsonPlaceholderUrlsEnum;
import com.typicode.jsonPlaceholder.api.posts.post.Post;
import com.typicode.jsonPlaceholder.api.posts.put.PostEntity;
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

    // https://jsonplaceholder.typicode.com/comments?postId={postId}
    ValidatableResponse getCommentsQueryParam(int postId, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .queryParam("postId", postId)
            .get(JsonPlaceholderUrlsEnum.COMMENTS.getUrl())
            .then()
            .log().body();
    }

    // https://jsonplaceholder.typicode.com/posts/{postId}/comments
    ValidatableResponse getCommentsPathParam(int postId, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .get(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postId + "/" + "comments")
            .then()
            .log().body();
    }

    ValidatableResponse post(Post post, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .body(post)
            .when()
            .post(JsonPlaceholderUrlsEnum.POSTS.getUrl())
            .then()
            .log().body();
    }

    ValidatableResponse put(PostEntity post, int postId, String uuid) {
        return RestAssured.given()
            .log().all()
            .header("test-uuid", uuid)
            .body(post)
            .put(JsonPlaceholderUrlsEnum.POSTS.getUrl() + "/" + postId)
            .then()
            .log().body();
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
