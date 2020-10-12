package com.terraqt.open.api.sdk.proxy;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.terraqt.open.api.sdk.annotation.Api;
import com.terraqt.open.api.sdk.config.OpenApiConfig;
import com.terraqt.open.api.sdk.exception.OpenApiException;
import com.terraqt.open.api.sdk.rpc.RpcCaller;
import com.terraqt.open.api.sdk.rpc.protocol.RequestPayload;
import com.terraqt.open.api.sdk.rpc.protocol.ResponsePayload;
import com.terraqt.open.api.sdk.util.json.JsonHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Map;

public class ApiProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Api apiAnnotation = method.getAnnotation(Api.class);
        if (null == apiAnnotation) {
            return null;
        }
        RequestPayload requestPayload = RequestPayload.createRequestPayload(collectParameters(method, args));
        ResponsePayload responsePayload = RpcCaller.call(
                OpenApiConfig.getConfig().getOpenUrl(),
                apiAnnotation.path(),
                OpenApiConfig.getConfig().getKey(),
                OpenApiConfig.getConfig().getSecret(),
                requestPayload
        );
        return getData(responsePayload, method);
    }

    private Map<String, Object> collectParameters(Method method, Object[] args) throws OpenApiException {
        if (null == args || args.length == 0) {
            return null;
        }
        Parameter[] parameters = method.getParameters();
        if (null == parameters || parameters.length == 0) {
            return null;
        }
        if (parameters.length != args.length) {
            throw new OpenApiException("Parameter count error.");
        }
        Map<String, Object> result = Maps.newHashMap();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            result.put(parameter.getName(), arg);
        }
        return result;
    }

    protected Object getData(ResponsePayload responsePayload, Method method) throws OpenApiException {
        if (null != responsePayload.getError()) {
            throw new OpenApiException(responsePayload.getError());
        }
        if (null == responsePayload.getData()) {
            return null;
        }
        if (isCollection(method.getReturnType())) {
            return JsonHelper.convert(responsePayload.getData(), TypeToken.of(method.getGenericReturnType()));
        }
        return JsonHelper.convert(responsePayload.getData(), method.getReturnType());
    }

    private boolean isCollection(Class returnType) {
        return Collection.class.isAssignableFrom(returnType) || Map.class.isAssignableFrom(returnType);
    }

}
