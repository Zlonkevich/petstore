package com.petstore.common.config;


import java.nio.file.InvalidPathException;

public class PetstoreConfig {
    private static final String PETSTORE_STAGE_BASE_URL = "https://petstore.swagger.io/v2/";
    private static final String PETSTORE_DEV_BASE_URL = "https://dev.petstore.swagger.io/v2/";
    private static final String PETSTORE_PROD_BASE_URL = "https://prod.petstore.swagger.io/v2/";

    private static final String ENV = System.getProperty("env");

    public static String getPetstoreBaseUrl() {
        switch (ENV) {
            case "stage" -> {
                return PETSTORE_STAGE_BASE_URL;
            }
            case "dev" -> {
                return PETSTORE_DEV_BASE_URL;
            }
            case "prod" -> {
                return PETSTORE_PROD_BASE_URL;
            }
            default -> throw new InvalidPathException(ENV, "Invalid 'env' system property value");
        }
    }
}
