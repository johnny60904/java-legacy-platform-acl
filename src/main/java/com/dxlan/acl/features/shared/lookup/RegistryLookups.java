package com.dxlan.acl.features.shared.lookup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class RegistryLookups {

    private RegistryLookups() {
        throw new AssertionError();
    }

    public static <T, V> Map<Class<? extends T>, V> buildClassRegistry(
            final Iterable<Class<? extends T>> keys,
            final Function<Class<? extends T>, V> strategyProvider
    ) {
        Objects.requireNonNull(keys, "Keys must not be null.");
        Objects.requireNonNull(strategyProvider, "StrategyProvider must not be null.");
        Map<Class<? extends T>, V> map = new HashMap<>();
        for (Class<? extends T> clazz : keys) {
            V strategy = strategyProvider.apply(clazz);
            if (strategy != null) {
                map.put(clazz, strategy);
            }
        }
        return Collections.unmodifiableMap(map);
    }

}
