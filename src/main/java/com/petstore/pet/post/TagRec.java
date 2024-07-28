package com.petstore.pet.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

public record TagRec(Long id,
                     String name) {

    public boolean isEqualTo(TagData tagData) {
        assertEquals(tagData.getId(), id);
        assertEquals(tagData.getName(), name);

        return true;
    }
}
