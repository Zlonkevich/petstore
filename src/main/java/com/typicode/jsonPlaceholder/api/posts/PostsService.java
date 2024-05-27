package com.typicode.jsonPlaceholder.api.posts;

import com.typicode.jsonPlaceholder.api.posts.get.ErrorResponse;
import com.typicode.jsonPlaceholder.api.posts.get.Post;
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


    @Step("Invoking 'GET /posts', waiting code 200")
    public Post[] getPosts200(int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");
        return postsController
            .get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(Post[].class);
    }


    @Step("Invoking 'GET /posts/{postNumber}', waiting code 200")
    public Post getPost(String postNumber, int code, String uuid) {
        return postsController
            .get(postNumber, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(Post.class);
    }

    public Post[] getPostsPosResp(int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of positive range '200 <= code < 300'");

        return postsController.get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(Post[].class);
    }

    public ErrorResponse getPostsErrResp(int code, String uuid) {
        assertTrue(400 <= code, "Sent code: " + code + " out of error range '400 <= code'");

        return postsController.get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(ErrorResponse.class);
    }

    public Post[] getComments(String postNumber, int code, String uuid) {
        return postsController.getComments(postNumber, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(Post[].class);
    }

}
