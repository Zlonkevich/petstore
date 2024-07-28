package com.typicode.jsonPlaceholder.api;

import com.petstore.common.config.PetstoreConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonPlaceholderUrlsEnum {
    POSTS(PetstoreConfig.getPetstoreBaseUrl() + "posts"),
    COMMENTS(PetstoreConfig.getPetstoreBaseUrl() + "comments"),
    ALBUMS(PetstoreConfig.getPetstoreBaseUrl() + "albums"),
    PHOTOS(PetstoreConfig.getPetstoreBaseUrl() + "photos"),
    TODOS(PetstoreConfig.getPetstoreBaseUrl() + "todos"),
    USERS(PetstoreConfig.getPetstoreBaseUrl() + "users");

    private final String url;
}
