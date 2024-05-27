package com.typicode.jsonPlaceholder.api.posts.get;

import lombok.Builder;


@Builder
public record PostRec(String userId,
                      String id,
                      String title,
                      String body) {
}
