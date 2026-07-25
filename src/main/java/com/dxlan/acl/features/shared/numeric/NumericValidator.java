package com.dxlan.acl.features.shared.numeric;

import java.util.Objects;

public final class NumericValidator {

    private static final String DEFAULT_NAME = "Value";

    private NumericValidator() {
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

    private static void validateNotNull(
            final Object target,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(target, name + " must not be null.");
    }

    private static void validateNotNull(
            final Object target
    ) {
        validateNotNull(target, DEFAULT_NAME);
    }

    public static <T extends Number> void validatePositive(
            final T value,
            final String name
    ) {
        validateNotNull(value, name);
        if (NumberPredicate.signum(value) <= 0) {
            throw new IllegalArgumentException(
                    name + " must be positive (> 0), but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validatePositive(
            final T value
    ) {
        validatePositive(value, DEFAULT_NAME);
    }

    public static <T extends Number> void validatePositiveOrZero(
            final T value,
            final String name
    ) {
        validateNotNull(value, name);
        if (NumberPredicate.signum(value) < 0) {
            throw new IllegalArgumentException(
                    name + " must be positive or zero (>= 0), but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validatePositiveOrZero(
            final T value
    ) {
        validatePositiveOrZero(value, DEFAULT_NAME);
    }

    public static <T extends Number> void validateAtMost(
            final T value,
            final T upperBound,
            final String name
    ) {
        validateNotNull(value, name);
        validateNotNull(upperBound, "UpperBound");
        if (NumberComparator.compare(value, upperBound) > 0) {
            throw new IllegalArgumentException(
                    name + " must be <= " + upperBound + ", but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateAtMost(
            final T value,
            final T upperBound
    ) {
        validateAtMost(value, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateLessThan(
            final T value,
            final T upperBound,
            final String name
    ) {
        validateNotNull(value, name);
        validateNotNull(upperBound, "UpperBound");
        if (NumberComparator.compare(value, upperBound) >= 0) {
            throw new IllegalArgumentException(
                    name + " must be < " + upperBound + ", but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateLessThan(
            final T value,
            final T upperBound
    ) {
        validateLessThan(value, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateAtLeast(
            final T value,
            final T lowerBound,
            final String name
    ) {
        validateNotNull(value, name);
        validateNotNull(lowerBound, "LowerBound");
        if (NumberComparator.compare(value, lowerBound) < 0) {
            throw new IllegalArgumentException(
                    name + " must be >= " + lowerBound + ", but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateAtLeast(
            final T value,
            final T lowerBound
    ) {
        validateAtLeast(value, lowerBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateGreaterThan(
            final T value,
            final T lowerBound,
            final String name
    ) {
        validateNotNull(value, name);
        validateNotNull(lowerBound, "LowerBound");
        if (NumberComparator.compare(value, lowerBound) <= 0) {
            throw new IllegalArgumentException(
                    name + " must be > " + lowerBound + ", but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateGreaterThan(
            final T value,
            final T lowerBound
    ) {
        validateGreaterThan(value, lowerBound, DEFAULT_NAME);
    }

}
