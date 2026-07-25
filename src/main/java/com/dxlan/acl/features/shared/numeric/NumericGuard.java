package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.objects.ObjectValidator;

public final class NumericGuard {

    private NumericGuard() {
        throw new AssertionError();
    }

    public static <T extends Number> T requireNotNull(
            final T value,
            final String name
    ) {
        ObjectValidator.validateNotNull(value, name);
        return value;
    }

    public static <T extends Number> T requireNotNull(
            final T value
    ) {
        ObjectValidator.validateNotNull(value);
        return value;
    }

    public static <T extends Number> T requirePositive(
            final T value,
            final String name
    ) {
        NumericValidator.validatePositive(value, name);
        return value;
    }

    public static <T extends Number> T requirePositive(
            final T value
    ) {
        NumericValidator.validatePositive(value);
        return value;
    }

    public static <T extends Number> T requirePositiveOrZero(
            final T value,
            final String name
    ) {
        NumericValidator.validatePositiveOrZero(value, name);
        return value;
    }

    public static <T extends Number> T requirePositiveOrZero(
            final T value
    ) {
        NumericValidator.validatePositiveOrZero(value);
        return value;
    }

    public static <T extends Number> T requireAtMost(
            final T value,
            final T upperBound,
            final String name
    ) {
        NumericValidator.validateAtMost(value, upperBound);
        return value;
    }

    public static <T extends Number> T requireAtMost(
            final T value,
            final T upperBound
    ) {
        NumericValidator.validateAtMost(value, upperBound);
        return value;
    }

    public static <T extends Number> T requireLessThan(
            final T value,
            final T upperBound,
            final String name
    ) {
        NumericValidator.validateLessThan(value, upperBound, name);
        return value;
    }

    public static <T extends Number> T requireLessThan(
            final T value,
            final T upperBound
    ) {
        NumericValidator.validateLessThan(value, upperBound);
        return value;
    }

    public static <T extends Number> T requireAtLeast(
            final T value,
            final T lowerBound,
            final String name
    ) {
        NumericValidator.validateAtLeast(value, lowerBound, name);
        return value;
    }

    public static <T extends Number> T requireAtLeast(
            final T value,
            final T lowerBound
    ) {
        NumericValidator.validateAtLeast(value, lowerBound);
        return value;
    }

    public static <T extends Number> T requireGreaterThan(
            final T value,
            final T lowerBound,
            final String name
    ) {
        NumericValidator.validateGreaterThan(value, lowerBound, name);
        return value;
    }

    public static <T extends Number> T requireGreaterThan(
            final T value,
            final T lowerBound
    ) {
        NumericValidator.validateGreaterThan(value, lowerBound);
        return value;
    }

}
