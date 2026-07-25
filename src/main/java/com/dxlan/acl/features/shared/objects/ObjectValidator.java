package com.dxlan.acl.features.shared.objects;

import java.util.Objects;

public final class ObjectValidator {

    private static final String DEFAULT_NAME = "Value";

    private ObjectValidator() {
        throw new AssertionError();
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name must not be blank."
            );
        }
    }

    public static void validateNotNull(
            final Object value,
            final String name
    ) {
        validateNameHasText(name);
        if (value == null) {
            throw new IllegalArgumentException(
                    name + " must not be null."
            );
        }
    }

    public static void validateNotNull(
            final Object value
    ) {
        validateNotNull(value, DEFAULT_NAME);
    }

    public static void validateSpecified(
            final Object value,
            final String name
    ) {
        validateNameHasText(name);
        if (value == null) {
            throw new IllegalArgumentException(
                    name + " must not be specified."
            );
        }
    }

    public static void validateSpecified(
            final Object value
    ) {
        validateSpecified(value, DEFAULT_NAME);
    }

}
