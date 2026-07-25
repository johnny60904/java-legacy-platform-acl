package com.dxlan.acl.features.shared.numeric;

import java.math.BigDecimal;
import java.util.Objects;

public final class BigDecimalValidator {

    private static final String DEFAULT_NAME = "BigDecimal";

    private static final int MAX_ALLOWED_SCALE = 10_000;
    private static final int MAX_ALLOWED_PRECISION = 10_000;

    private BigDecimalValidator() {
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

    /// to prevent Java CWE-400 (uncontrolled resource consumption) security vulnerability
    public static void validateScaleBelowUpperBound(
            final BigDecimal bigDecimal,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(bigDecimal, name + " must not be null.");
        if (Math.abs(bigDecimal.scale()) > MAX_ALLOWED_SCALE) {
            throw new IllegalArgumentException(
                    name + "'s big decimal scale is too large, rejected to prevent OOM."
            );
        }
    }

    public static void validateScaleBelowUpperBound(
            final BigDecimal bigDecimal
    ) {
        Objects.requireNonNull(bigDecimal, DEFAULT_NAME + " must not be null.");
        if (Math.abs(bigDecimal.scale()) > MAX_ALLOWED_SCALE) {
            throw new IllegalArgumentException(
                    DEFAULT_NAME + " scale is too large, rejected to prevent OOM."
            );
        }
    }

    /// to prevent Java CWE-400 (uncontrolled resource consumption) security vulnerability
    public static void validatePrecisionBelowUpperBound(
            final BigDecimal bigDecimal,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(bigDecimal, name + " must not be null.");
        if (bigDecimal.precision() > MAX_ALLOWED_PRECISION) {
            throw new IllegalArgumentException(
                    name + "'s big decimal precision is too large, rejected to prevent OOM."
            );
        }
    }

    public static void validatePrecisionBelowUpperBound(
            final BigDecimal bigDecimal
    ) {
        Objects.requireNonNull(bigDecimal, DEFAULT_NAME + " must not be null.");
        if (bigDecimal.precision() > MAX_ALLOWED_PRECISION) {
            throw new IllegalArgumentException(
                    DEFAULT_NAME + " precision is too large, rejected to prevent OOM."
            );
        }
    }

}
