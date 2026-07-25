package com.dxlan.acl.features.shared.lookup;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public final class LookupValidator {

    private LookupValidator() { throw new AssertionError(); }

    public static <K, V, X extends RuntimeException> V getOrThrow(
            final K key,
            final Function<K, V> lookupMapFetcher,
            final Supplier<? extends X> exceptionSupplier
    ) {
        Objects.requireNonNull(key, "Key must not be null.");
        Objects.requireNonNull(lookupMapFetcher, "LookupMapFetcher must not be null.");
        Objects.requireNonNull(exceptionSupplier, "ExceptionSupplier must not be null.");
        V result = lookupMapFetcher.apply(key);
        if (result == null) throw exceptionSupplier.get();
        return result;
    }

    public static <K, R, X extends RuntimeException> Function<List<Object>, R> fromMapOrThrow(
            final K key,
            final Map<K, Function<List<Object>, R>> lookupMap,
            final Supplier<? extends X> exceptionSupplier
    ) {
        Objects.requireNonNull(key, "Key must not be null.");
        Objects.requireNonNull(lookupMap, "LookupMap must not be null.");
        Objects.requireNonNull(exceptionSupplier, "ExceptionSupplier must not be null.");
        Function<List<Object>, R> function = lookupMap.get(key);
        if (function == null) throw exceptionSupplier.get();
        return function;
    }

}
