package com.dxlan.acl.features.shared.numeric;

import java.util.Objects;

public final class DoubleNumberValidator {

    private static final String DEFAULT_NAME = "DoubleValue";

    private DoubleNumberValidator() {
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
            final Double doubleValue,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(doubleValue, name + " must not be null.");
        if (Double.isNaN(doubleValue)) {
            throw new IllegalArgumentException("Double value for " + name + " must not be NaN.");
        }
    }

    public static void validateNotNaN(
            final Double doubleValue
    ) {
        Objects.requireNonNull(doubleValue, DEFAULT_NAME + " must not be null.");
        if (Double.isNaN(doubleValue)) {
            throw new IllegalArgumentException("Double value must not be NaN.");
        }
    }

    public static void validateFinite(
            final Double doubleValue,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(doubleValue, name + " must not be null.");
        if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
            throw new IllegalArgumentException("Double value for " + name + " must not be NaN or Infinity.");
        }
    }

    public static void validateFinite(
            final Double doubleValue
    ) {
        Objects.requireNonNull(doubleValue, DEFAULT_NAME + " must not be null.");
        if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
            throw new IllegalArgumentException("Double value must not be NaN or Infinity.");
        }
    }

}
