package com.dxlan.acl.features.shared.numeric;

import java.util.Objects;

public final class FloatNumberValidator {

    private static final String DEFAULT_NAME = "FloatValue";

    private FloatNumberValidator() {
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

    public static void validateNotNaN(
            final Float floatValue,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(floatValue, name + " must not be null.");
        if (Float.isNaN(floatValue)) {
            throw new IllegalArgumentException("Float value for " + name + " must not be NaN.");
        }
    }

    public static void validateNotNaN(
            final Float floatValue
    ) {
        Objects.requireNonNull(floatValue, DEFAULT_NAME + " must not be null.");
        if (Float.isNaN(floatValue)) {
            throw new IllegalArgumentException("Float value must not be NaN.");
        }
    }

    public static void validateFinite(
            final Float floatValue,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(floatValue, name + " must not be null.");
        if (Float.isNaN(floatValue) || Float.isInfinite(floatValue)) {
            throw new IllegalArgumentException("Float value for " + name + " must not be NaN or Infinity.");
        }
    }

    public static void validateFinite(
            final Float floatValue
    ) {
        Objects.requireNonNull(floatValue, DEFAULT_NAME + " must not be null.");
        if (Float.isNaN(floatValue) || Float.isInfinite(floatValue)) {
            throw new IllegalArgumentException("Float value must not be NaN or Infinity.");
        }
    }

}
