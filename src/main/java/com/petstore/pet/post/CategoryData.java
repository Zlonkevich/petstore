package com.petstore.pet.post;

import lombok.Builder;
import lombok.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
@Builder
public class CategoryData {
    private Long id;

    private String name;

    public boolean isEqualTo(CategoryData categoryData) {
        assertEquals(categoryData.getId(), id);
        assertEquals(categoryData.getId(), name);

        return true;
    }
}
