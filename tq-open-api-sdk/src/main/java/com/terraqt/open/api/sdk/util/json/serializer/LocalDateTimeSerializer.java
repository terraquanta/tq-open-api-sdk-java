package com.terraqt.open.api.sdk.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime));
    }
}
