package com.petstore.pet.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagData {
    private Long id;

    private String name;
}
