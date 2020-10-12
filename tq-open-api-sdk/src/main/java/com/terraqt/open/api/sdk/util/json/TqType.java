package com.terraqt.open.api.sdk.util.json;

import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class TqType {
    private static final Logger logger = LogManager.getLogger(TqType.class);
    private TqType() {}

    private static final Map<String, Class<?>> TYPE_MAP = Maps.newHashMap();
    static {
        Primitives.allPrimitiveTypes().forEach(primitive ->
                TYPE_MAP.put(primitive.getName(), primitive));
    }

    public static Class<?> getType(String typeName) {
        if (!TYPE_MAP.containsKey(typeName)) {
            synchronized (TYPE_MAP) {
                if (!TYPE_MAP.containsKey(typeName)) {
                    Class<?> type = null;
                    try {
                        type = Class.forName(typeName);
                    } catch (ClassNotFoundException ex) {
                        // Do nothing
                    }
                    TYPE_MAP.put(typeName, type);
                }
            }
        }

        Class<?> type = TYPE_MAP.get(typeName);
        if (type == null) {
            logger.warn(String.format("Class [%s] not found.", typeName));
        }
        return type;
    }
}
