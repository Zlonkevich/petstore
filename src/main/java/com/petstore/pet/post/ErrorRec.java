package com.petstore.pet.post;

public record ErrorRec(Integer code,
                       String type,
                       String message) {
}
