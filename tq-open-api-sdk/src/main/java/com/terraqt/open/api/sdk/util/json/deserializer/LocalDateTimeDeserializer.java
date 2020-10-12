package com.terraqt.open.api.sdk.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import com.terraqt.open.api.sdk.util.StringUtil;
import com.terraqt.open.api.sdk.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateTimeStr = ((JsonNode) parser.getCodec().readTree(parser)).asText();
        if (!Strings.isNullOrEmpty(dateTimeStr) && StringUtil.isNumeric(dateTimeStr)) {
            return TimeUtil.toLocalDateTime(Long.parseLong(dateTimeStr));
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
