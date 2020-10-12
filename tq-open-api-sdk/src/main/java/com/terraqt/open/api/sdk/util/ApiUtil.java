package com.terraqt.open.api.sdk.util;

import com.terraqt.open.api.sdk.proxy.ApiProxy;

import java.lang.reflect.Proxy;

public class ApiUtil {
    private static final ClassLoader CLASS_LOADER = ApiUtil.class.getClassLoader();

    public static <T> T getApi(Class<T> tClass) {
        ApiProxy apiProxy = new ApiProxy();
        Class[] apis = new Class[]{tClass};
        return (T) Proxy.newProxyInstance(CLASS_LOADER, apis, apiProxy);
    }
}
