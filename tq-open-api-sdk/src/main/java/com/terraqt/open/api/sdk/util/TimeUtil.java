package com.terraqt.open.api.sdk.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public final class TimeUtil {
    private static final Integer SECOND_TIMESTAMP_LENGTH = 10;
    private static final Integer MILL_SECOND_TIMESTAMP_LENGTH = 13;
    private TimeUtil() {
    }

    private static Logger logger = LogManager.getLogger(TimeUtil.class);

    public static LocalDateTime toLocalDateTime(long unixTimestamp) {
        String timeValue = String.valueOf(unixTimestamp);
        if (timeValue.length() == SECOND_TIMESTAMP_LENGTH) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp * 1000), ZoneId.systemDefault());
        } else if (timeValue.length() == MILL_SECOND_TIMESTAMP_LENGTH) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), ZoneId.systemDefault());
        }

        logger.warn("unixTimestamp[%s] not a standard format, should be 10 or 13 digits", timeValue);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp * 1000), ZoneId.systemDefault());
    }

    public static String format(TemporalAccessor time, String format) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(time);
    }
}
