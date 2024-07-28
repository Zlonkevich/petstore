package com.petstore.pet.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestData {
    private Long id;

    private CategoryData category;

    private String name;

    private List<String> photoUrls;

    private List<TagData> tags;

    private String status;
}
