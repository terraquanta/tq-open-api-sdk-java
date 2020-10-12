package com.terraqt.open.api.sdk.util.json;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class TqToken {
    private TqToken() {}

    public static <E> TypeToken<List<E>> listOf(TypeToken<E> elemToken) {
        return (new TypeToken<List<E>>() {})
                .where(new TypeParameter() {}, elemToken);
    }

    public static <E> TypeToken<Set<E>> setOf(TypeToken<E> elemToken) {
        return (new TypeToken<Set<E>>() {})
                .where(new TypeParameter() {}, elemToken);
    }

    public static <K, V> TypeToken<Map<K, V>> mapOf(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return (new TypeToken<Map<K, V>>() {})
                .where(new TypeParameter() {}, keyToken)
                .where(new TypeParameter() {}, valueToken);
    }
}
