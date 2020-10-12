package com.terraqt.open.api.sdk.rpc;

import com.terraqt.open.api.sdk.exception.OpenApiException;
import com.terraqt.open.api.sdk.rpc.protocol.RequestPayload;
import com.terraqt.open.api.sdk.rpc.protocol.ResponsePayload;
import com.terraqt.open.api.sdk.sign.SignHelper;
import com.terraqt.open.api.sdk.util.json.JsonHelper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RpcCaller {

    private static final int STATUS_CODE = 200;

    public static ResponsePayload call(String url, String path, String key, String secret, RequestPayload payload) throws OpenApiException, IOException {
        String payloadJson = JsonHelper.writeAsString(payload);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String signature = SignHelper.calculateSignature(key, secret, timestamp, payloadJson);
        HttpPost httpPost = buildPost(url + path, key, timestamp, signature, payloadJson);
        String response = call(httpPost);
        return JsonHelper.read(response, ResponsePayload.class);
    }

    private static String call(HttpPost httpPost) throws OpenApiException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() == STATUS_CODE) {
                    return EntityUtils.toString(response.getEntity());
                } else {
                    throw new OpenApiException(String.format("Call failed, status code:%d", response.getStatusLine().getStatusCode()));
                }
            }
        }
    }

    private static HttpPost buildPost(String url, String key, String timestamp, String signature, String requestBody) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.setHeader("TQ-Key", key);
        post.setHeader("TQ-TimeMilli", timestamp);
        post.setHeader("TQ-Signature", signature);
        post.setEntity(new StringEntity(requestBody));
        return post;
    }

}
