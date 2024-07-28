package com.petstore.pet.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

public record CategoryRec(Long id,
                          String name) {

    public boolean isEqualTo(CategoryData categoryData) {
        assertEquals(categoryData.getId(), id);
        assertEquals(categoryData.getName(), name);

        return true;
    }
}
