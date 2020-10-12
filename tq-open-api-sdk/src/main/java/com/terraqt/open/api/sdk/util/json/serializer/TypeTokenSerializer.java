package com.terraqt.open.api.sdk.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public final class TypeTokenSerializer extends JsonSerializer<TypeToken> {
    private String serialize(Type type) {
        if (type instanceof Class) {
            return ((Class)type).getName();
        }

        if (type instanceof ParameterizedType) {
            Class<?> containerType = (Class<?>)((ParameterizedType)type).getRawType();
            Type[] actualTypes = ((ParameterizedType)type).getActualTypeArguments();

            if (List.class.isAssignableFrom(containerType)) {
                checkArgument(containerType == List.class, "VJson only support List.class, not its subclasses.");
                return String.format("L(%s)", serialize(actualTypes[0]));
            }

            if (Set.class.isAssignableFrom(containerType)) {
                checkArgument(containerType == Set.class, "VJson only support Set.class, not its subclasses.");
                return String.format("S(%s)", serialize(actualTypes[0]));
            }

            if (Map.class.isAssignableFrom(containerType)) {
                checkArgument(containerType == Map.class, "VJson only support Map.class, not its subclasses.");
                return String.format("M(%s,%s)", serialize(actualTypes[0]), serialize(actualTypes[1]));
            }
        }

        throw new IllegalArgumentException("Unexpected type.");
    }

    @Override
    public void serialize(TypeToken token, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(serialize(token.getType()));
    }
}
