package com.terraqt.open.api.sdk.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class LocalTimeSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(DateTimeFormatter.ISO_LOCAL_TIME.format(time));
    }
}
