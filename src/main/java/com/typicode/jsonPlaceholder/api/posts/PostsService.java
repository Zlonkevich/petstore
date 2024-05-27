package com.typicode.jsonPlaceholder.api.posts;

import com.typicode.jsonPlaceholder.api.comments.PostCommentRec;
import com.typicode.jsonPlaceholder.api.posts.get.ErrorResponse;
import com.typicode.jsonPlaceholder.api.posts.get.PostRec;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Service
public class PostsService {
    @Autowired
    private PostsController postsController;


    @Step("Invoking 'GET /posts', waiting for code 200")
    public PostRec[] getPosts200(int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController
            .get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec[].class);
    }


    @Step("Invoking 'GET /posts/{postNumber}', waiting for code 200")
    public PostRec getPost200(int postNumber, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController
            .get(postNumber, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec.class);
    }

    public PostRec[] getPostsPosResp(int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController.get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec[].class);
    }

    public ErrorResponse getPostsErrResp(int code, String uuid) {
        assertTrue(400 <= code, "Sent code: " + code + " out of error range '400 <= code'");

        return postsController.get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(ErrorResponse.class);
    }

    public PostCommentRec[] getPostComments200(int postId, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController.getComments(postId, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostCommentRec[].class);
    }

    public PostCommentRec[] getCommentByPostId200(int postId, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController.getComments(postId, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostCommentRec[].class);
    }

}
