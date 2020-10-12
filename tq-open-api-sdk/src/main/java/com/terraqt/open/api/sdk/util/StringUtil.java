package com.terraqt.open.api.sdk.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String value) {
        return null == value || value.isEmpty();
    }

    public static boolean isNumeric(String string) {
        return string != null && string.matches("-?\\d+(\\.\\d+)?");
    }

}
