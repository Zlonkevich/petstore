package com.typicode.jsonPlaceholder.api.posts.get;

import lombok.Builder;


@Builder
public record PostRec200s(String userId,
                          String id,
                          String title,
                          String body) {
}
