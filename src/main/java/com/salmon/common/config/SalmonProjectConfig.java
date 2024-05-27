package com.salmon.common.config;

import lombok.Getter;

@Getter
public class SalmonProjectConfig {
    private static final String JSONPLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static String getJsonplaceholderBaseUrl() {
        return JSONPLACEHOLDER_BASE_URL;
    }
}
