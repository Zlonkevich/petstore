package com.typicode.jsonPlaceholder.api.posts;


import com.typicode.jsonPlaceholder.api.posts.get.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public enum PostsResponseCodeEnum {
    CODE_200(200, Post[].class),
    CODE_201(201, Post[].class),
    CODE_202(202, Post[].class);

    private final Integer code;
    private final Class respClass;

    public static Stream<PostsResponseCodeEnum> stream() {
        return Stream.of(PostsResponseCodeEnum.values());
    }

    public static PostsResponseCodeEnum getResponseClassByCode(Integer code) {
        return PostsResponseCodeEnum.stream()
            .filter(s -> s.getCode().equals(code))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "ResponseCode enum doesn't contain '" + code + "'"));
    }
}
