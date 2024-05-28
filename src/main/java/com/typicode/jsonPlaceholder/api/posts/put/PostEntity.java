package com.typicode.jsonPlaceholder.api.posts.put;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostEntity {
    private int id;
    private String title;
    private String body;
    private int userId;
}