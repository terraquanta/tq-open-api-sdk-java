package com.terraqt.open.api.sdk.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.reflect.TypeToken;
import com.terraqt.open.api.sdk.util.json.deserializer.LocalDateDeserializer;
import com.terraqt.open.api.sdk.util.json.deserializer.LocalDateTimeDeserializer;
import com.terraqt.open.api.sdk.util.json.deserializer.LocalTimeDeserializer;
import com.terraqt.open.api.sdk.util.json.deserializer.TypeTokenDeserializer;
import com.terraqt.open.api.sdk.util.json.serializer.LocalDateSerializer;
import com.terraqt.open.api.sdk.util.json.serializer.LocalDateTimeSerializer;
import com.terraqt.open.api.sdk.util.json.serializer.LocalTimeSerializer;
import com.terraqt.open.api.sdk.util.json.serializer.TypeTokenSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("unchecked")
public final class JsonHelper {
    private JsonHelper() {}

    private static Logger logger = LogManager.getLogger(JsonHelper.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addSerializer(TypeToken.class, new TypeTokenSerializer());

        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addDeserializer(TypeToken.class, new TypeTokenDeserializer());
        MAPPER.registerModule(module);
    }

    private static <T> T read(String json, JavaType javaType) {
        try {
            return json != null ? MAPPER.readValue(json, javaType) : null;
        } catch (IOException ex) {
            logger.warn("String [%s] cannot be converted to Type [%s]", json, javaType);
            return null;
        }
    }

    public static <T> T read(String json, TypeToken typeToken) {
        return read(json, MAPPER.constructType(typeToken.getType()));
    }

    public static <T> T read(String json, Class<T> type) {
        return read(json, MAPPER.constructType(type));
    }

    private static <T> T convert(Object fromJson, JavaType javaType) {
        try {
            return fromJson != null ? MAPPER.convertValue(fromJson, javaType) : null;
        } catch (IllegalArgumentException ex) {
            logger.warn("Object [%s] cannot be converted to Type [%s]", fromJson, javaType);
            return null;
        }
    }

    public static <T> T convert(Object fromJson, TypeToken typeToken) {
        return convert(fromJson, MAPPER.constructType(typeToken.getType()));
    }

    public static <T> T convert(Object fromJson, Class<T> type) {
        return convert(fromJson, MAPPER.constructType(type));
    }

    public static String writeAsString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(String.format("Fail to convert Object [%s] to json string", object.getClass().getName()), ex);
        }
    }

    public static boolean isJsonValid(String json) {
        try {
            MAPPER.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
