package com.petstore.pet.petId.uploadImage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestData {
    private Long petId;

    private String additionalMetadata;

    private File file;
}
