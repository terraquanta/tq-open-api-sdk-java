package com.terraqt.open.api.sdk.rpc.protocol;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestPayload {
    private String requestId;
    private Object params;

    public static RequestPayload createRequestPayload(Object params) {
        RequestPayload payload = new RequestPayload();
        payload.setRequestId(UUID.randomUUID().toString());
        payload.setParams(params);
        return payload;
    }
}
