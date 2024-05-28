package com.typicode.jsonPlaceholder.api.posts;

import com.typicode.jsonPlaceholder.api.comments.PostCommentRec200s;
import com.typicode.jsonPlaceholder.api.posts.get.PostRec200s;
import com.typicode.jsonPlaceholder.api.posts.post.Post;
import com.typicode.jsonPlaceholder.api.posts.post.PostResponse200s;
import com.typicode.jsonPlaceholder.api.posts.put.PostEntity;
import com.typicode.jsonPlaceholder.api.posts.put.PutResponse200s;
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


    @Step("Invoking 'GET /posts', waiting for code 200s")
    public PostRec200s[] getPosts200s(int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of  range '200 <= code < 300'");

        return postsController
            .get(uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec200s[].class);
    }

    @Step("Invoking 'GET /posts/{postNumber}', waiting for code 200s")
    public PostRec200s getPost200s(int postNumber, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController
            .get(postNumber, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec200s.class);
    }

    @Step("Invoking 'GET /posts/{postNumber}', waiting for code 400s")
    public PostRec200s getPost400s(int postNumber, int code, String uuid) {
        assertTrue(400 <= code && code < 500, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController
            .get(postNumber, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostRec200s.class);
    }

    @Step("Invoking 'GET /posts/{postId}/comments', waiting for code 200s")
    public PostCommentRec200s[] getPostCommentsPathParam200s(int postId, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController.getCommentsPathParam(postId, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostCommentRec200s[].class);
    }

    @Step("Invoking 'GET /comments?postId={postId}', waiting for code 200s")
    public PostCommentRec200s[] getPostCommentsQueryParam200s(int postId, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController.getCommentsQueryParam(postId, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostCommentRec200s[].class);
    }

    @Step("Invoking 'POST /posts', waiting for code 200s")
    public PostResponse200s postPost200s(Post post, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController.post(post, uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PostResponse200s.class);
    }

    @Step("Invoking 'PUT /posts/{postId}', waiting for code 200s")
    public PutResponse200s putPost200s(PostEntity post, int code, String uuid) {
        assertTrue(200 <= code && code < 300, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController.put(post, post.getId(), uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PutResponse200s.class);
    }

    @Step("Invoking 'PUT /posts/{postId}', waiting for code 400s")
    public PutResponse200s putPost400s(PostEntity post, int code, String uuid) {
        assertTrue(400 <= code && code < 500, "Sent code: " + code + " out of range '200 <= code < 300'");

        return postsController.put(post, post.getId(), uuid)
            .statusCode(code)
            .extract()
            .body()
            .as(PutResponse200s.class);
    }


}
