package com.petstore.pet;

import com.petstore.common.config.PetstoreConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetUrlsEnum {
    PET(PetstoreConfig.getPetstoreBaseUrl() + "pet"),
    FIND_BY_STATUS(PetstoreConfig.getPetstoreBaseUrl() + "pet/findByStatus");

    private final String url;
}