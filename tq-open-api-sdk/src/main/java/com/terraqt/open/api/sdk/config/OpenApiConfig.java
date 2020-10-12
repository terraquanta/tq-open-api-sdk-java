package com.terraqt.open.api.sdk.config;

import com.terraqt.open.api.sdk.exception.OpenApiException;
import lombok.Getter;

@Getter
public class OpenApiConfig {
    private final String openUrl = "https://api.terraqt.com";
    private String key;
    private String secret;

    private static final OpenApiConfig OPEN_API_CONFIG = new OpenApiConfig();

    public static void configure(String key, String secret) throws OpenApiException {
        if (null == key || null == secret) {
            throw new OpenApiException("key or secret is invalid");
        }
        OPEN_API_CONFIG.key = key;
        OPEN_API_CONFIG.secret = secret;
    }

    public static OpenApiConfig getConfig() {
        return OPEN_API_CONFIG;
    }
}
