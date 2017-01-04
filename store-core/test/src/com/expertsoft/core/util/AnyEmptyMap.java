package com.expertsoft.core.util;

import java.util.Map;

import org.mockito.ArgumentMatcher;

public final class AnyEmptyMap<K, V> implements ArgumentMatcher<Map<K, V>> {

    @Override
    public boolean matches(Map<K, V> map) {
        return map.isEmpty();
    }

}
