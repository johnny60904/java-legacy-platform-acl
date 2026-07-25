package com.dxlan.acl.features.shared.collections;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class SetJoiner {

    private static final String DEFAULT_NAME = "Set";

    private SetJoiner() {
        throw new AssertionError();
    }

    public static <T> String join(
            final Set<T> set,
            final String delimiter
    ) {
        CollectionValidator.validateNotNull(set, DEFAULT_NAME);
        Objects.requireNonNull(delimiter, "Delimiter must not be null.");
        if (set.isEmpty()) return "";
        CollectionValidator.validateSizeBelowSecurityBound(set, DEFAULT_NAME);
        return set.stream().map(String::valueOf).collect(Collectors.joining(delimiter));
    }

    public static <T> String join(
            final Set<T> set
    ) {
        return join(set, ", ");
    }

}
