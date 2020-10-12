package com.terraqt.open.api.sdk.rpc.protocol;

import lombok.Data;

@Data
public class ResponsePayload {
    private static final String SUCCESS_CODE = "SUCCEED";
    private static final String ERROR_CODE = "ERROR";
    private static final String DEFAULT_ERROR_MSG = "服务器错误";

    private String code;
    private Object data;
    private String error;


    public static ResponsePayload success(Object data) {
        ResponsePayload responsePayload = new ResponsePayload();
        responsePayload.setCode(SUCCESS_CODE);
        responsePayload.setData(data);
        responsePayload.setError(null);
        return responsePayload;
    }

    public static ResponsePayload error(String errorMsg) {
        ResponsePayload responsePayload = new ResponsePayload();
        responsePayload.setCode(ERROR_CODE);
        responsePayload.setData(null);
        if (null == errorMsg || errorMsg.isEmpty()) {
            errorMsg = DEFAULT_ERROR_MSG;
        }
        responsePayload.setError(errorMsg);
        return responsePayload;
    }
}
