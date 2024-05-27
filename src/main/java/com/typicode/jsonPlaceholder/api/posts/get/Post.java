package com.typicode.jsonPlaceholder.api.posts.get;

import lombok.Builder;


@Builder
public record Post(String userId,
                   String id,
                   String title,
                   String body) {
}
