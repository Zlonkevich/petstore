package com.petstore.pet.post;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public record PostResponse200sRec(Long id,
                                  CategoryRec category,
                                  String name,
                                  List<String> photoUrls,
                                  List<TagRec> tags,
                                  String status) {


    public boolean isEqualTo(RequestData request) {
        assertEquals(request.getId(), id);
        assertTrue(category.isEqualTo(request.getCategory()));
        assertEquals(request.getName(), name);

        request.getPhotoUrls().forEach(url -> assertTrue(photoUrls.contains(url)));

        tags.forEach(tag -> assertTrue(tag.isEqualTo(request.getTags().stream()
                                                         .filter(t -> tag.id().equals(t.getId()))
                                                         .findFirst()
                                                         .get())));
        assertEquals(request.getStatus(), status);

        return true;
    }
}
