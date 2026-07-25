package com.dxlan.acl.features.shared.maps;

import java.util.Map;
import java.util.Objects;

public final class MapValidator {

    private static final String DEFAULT_NAME = "Map";

    private MapValidator() {
        throw new AssertionError();
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
    }

    public static <T extends Map<?, ?>> void validateNotNull(
            final T map,
            final String name
    ) {
        validateNameHasText(name);
        if (map == null) {
            throw new IllegalArgumentException(
                    name + " must not be null."
            );
        }
    }

    public static <T extends Map<?, ?>> void validateNotNull(
            final T map
    ) {
        validateNotNull(map, DEFAULT_NAME);
    }

    public static <T extends Map<?, ?>> void validateNotEmpty(
            final T map,
            final String name
    ) {
        validateNotNull(map, name);
        if (map.isEmpty()) {
            throw new IllegalArgumentException(
                    name + " must not be empty."
            );
        }
    }

    public static <T extends Map<?, ?>> void validateNotEmpty(
            final T map
    ) {
        validateNotEmpty(map, DEFAULT_NAME);
    }

}
