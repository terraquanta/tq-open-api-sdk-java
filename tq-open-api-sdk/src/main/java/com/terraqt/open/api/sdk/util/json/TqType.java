package com.terraqt.open.api.sdk.util.json;

import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class TqType {
    private static final Logger logger = LogManager.getLogger(TqType.class);
    private TqType() {}

    private static final Map<String, Class<?>> typeMap = Maps.newHashMap();
    static {
        Primitives.allPrimitiveTypes().forEach(primitive ->
                typeMap.put(primitive.getName(), primitive));
    }

    public static Class<?> getType(String typeName) {
        if (!typeMap.containsKey(typeName)) {
            synchronized (typeMap) {
                if (!typeMap.containsKey(typeName)) {
                    Class<?> type = null;
                    try {
                        type = Class.forName(typeName);
                    } catch (ClassNotFoundException ex) {
                        // Do nothing
                    }
                    typeMap.put(typeName, type);
                }
            }
        }

        Class<?> type = typeMap.get(typeName);
        if (type == null) {
            logger.warn(String.format("Class [%s] not found.", typeName));
        }
        return type;
    }
}
