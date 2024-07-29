package com.petstore.pet.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


/**
 * There is no misunderstanding here - an object can be created both using Builder pattern or
 * Chain of invocation <br/>
 * Depends on what code style you use on the project
 */
@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestData {
    private Object id;

    private CategoryData category;

    private String name;

    private List<String> photoUrls;

    private List<TagData> tags;

    private String status;

    public RequestData setCategory(CategoryData category) {
        this.category = category;
        return this;
    }

    public RequestData setName(String name) {
        this.name = name;
        return this;
    }

    public RequestData setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public RequestData setTags(List<TagData> tags) {
        this.tags = tags;
        return this;
    }

    public RequestData setStatus(String status) {
        this.status = status;
        return this;
    }

    public RequestData setId(Object id) {
        this.id = id;
        return this;
    }
}
