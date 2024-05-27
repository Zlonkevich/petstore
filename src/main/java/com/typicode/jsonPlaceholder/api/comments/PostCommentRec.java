package com.typicode.jsonPlaceholder.api.comments;

public record PostCommentRec(String postId,
                             String id,
                             String name,
                             String email,
                             String body) {
}
