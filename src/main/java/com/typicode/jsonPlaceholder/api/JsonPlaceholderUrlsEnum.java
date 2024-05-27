package com.typicode.jsonPlaceholder.api;

import com.salmon.common.config.SalmonProjectConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonPlaceholderUrlsEnum {
    POSTS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "posts"),
    COMMENTS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "comments"),
    ALBUMS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "albums"),
    PHOTOS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "photos"),
    TODOS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "todos"),
    USERS(SalmonProjectConfig.getJsonplaceholderBaseUrl() + "users");

    private final String url;
}
