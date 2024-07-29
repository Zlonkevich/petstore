package com.petstore.pet;

import com.petstore.pet.post.CategoryData;
import com.petstore.pet.post.RequestData;
import com.petstore.pet.post.TagData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetObjectFactory {

    // some logic here to create a pet request instance
    public RequestData getPetRequest() {
        return RequestData.builder()
            .id(0L)
            .category(CategoryData.builder()
                          .id(0L)
                          .name("string")
                          .build())
            .name("doggie")
            .photoUrls(List.of("url"))
            .tags(List.of(TagData.builder()
                              .id(0L)
                              .name("string")
                              .build()))
            .status("available")
            .build();
    }
}
