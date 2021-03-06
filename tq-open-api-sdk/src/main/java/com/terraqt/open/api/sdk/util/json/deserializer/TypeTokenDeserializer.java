package com.terraqt.open.api.sdk.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.terraqt.open.api.sdk.util.json.TqToken;
import com.terraqt.open.api.sdk.util.json.TqType;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeTokenDeserializer extends JsonDeserializer<TypeToken> {
    private static final Pattern PATTERN = Pattern.compile("^(?<containerType>[LSM])\\((?:(?<elemType>[^,]+)|(?<keyType>[^,]+),(?<valueType>[^,]+))\\)$");
    private static final String LIST_PREFIX = "L";
    private static final String SET_PREFIX = "S";
    private static final String MAP_PREFIX = "M";

    @SuppressWarnings("unchecked")
    private TypeToken deserialize(String tokenStr) {
        Matcher matcher = PATTERN.matcher(tokenStr);
        if (!matcher.matches()) {
            return TypeToken.of(TqType.getType(tokenStr));
        }

        String containerType = matcher.group("containerType");
        if (LIST_PREFIX.equalsIgnoreCase(containerType)) {
            return TqToken.listOf(deserialize(matcher.group("elemType")));
        }
        if (SET_PREFIX.equalsIgnoreCase(containerType)) {
            return TqToken.setOf(deserialize(matcher.group("elemType")));
        }
        if (MAP_PREFIX.equalsIgnoreCase(containerType)) {
            return TqToken.mapOf(deserialize(matcher.group("keyType")), deserialize(matcher.group("valueType")));
        }

        throw new IllegalStateException();
    }

    private final Map<String, TypeToken> tokenMap = Maps.newHashMap();

    private TypeToken toToken(String tokenStr) {
        if (!tokenMap.containsKey(tokenStr)) {
            synchronized (tokenMap) {
                if (!tokenMap.containsKey(tokenStr)) {
                    tokenMap.put(tokenStr, deserialize(tokenStr));
                }
            }
        }
        return tokenMap.get(tokenStr);
    }

    @Override
    public TypeToken deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String tokenStr = ((JsonNode)parser.getCodec().readTree(parser)).asText();
        return toToken(tokenStr);
    }
}
