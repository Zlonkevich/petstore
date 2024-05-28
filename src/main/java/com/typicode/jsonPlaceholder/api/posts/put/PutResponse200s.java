package com.typicode.jsonPlaceholder.api.posts.put;


import static org.junit.jupiter.api.Assertions.assertEquals;

public record PutResponse200s(int id,
                              String title,
                              String body,
                              int userId) {

    public boolean isEqualTo(PostEntity post) {
        assertEquals(post.getId(), id);
        assertEquals(post.getTitle(), title);
        assertEquals(post.getBody(), body);
        assertEquals(post.getUserId(), userId);
        return true;
    }
}
