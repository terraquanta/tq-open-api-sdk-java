package com.terraqt.open.api.sdk.exception;

public class OpenApiException extends Exception {
    public OpenApiException() {
    }

    public OpenApiException(String message) {
        super(message);
    }

    public OpenApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
