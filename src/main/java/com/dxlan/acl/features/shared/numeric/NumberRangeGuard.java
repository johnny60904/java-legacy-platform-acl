package com.dxlan.acl.features.shared.numeric;

public final class NumberRangeGuard {

    private NumberRangeGuard() {
        throw new AssertionError();
    }

    public static <T extends Number> T requireInClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        NumberRangeValidator.validateInClosedRange(value, lowerBound, upperBound, name);
        return value;
    }

    public static <T extends Number> T requireInClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        NumberRangeValidator.validateInClosedRange(value, lowerBound, upperBound);
        return value;
    }

    public static <T extends Number> T requireInOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        NumberRangeValidator.validateInOpenRange(value, lowerBound, upperBound, name);
        return value;
    }

    public static <T extends Number> T requireInOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        NumberRangeValidator.validateInOpenRange(value, lowerBound, upperBound);
        return value;
    }

    public static <T extends Number> T requireInClosedOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        NumberRangeValidator.validateInClosedOpenRange(value, lowerBound, upperBound, name);
        return value;
    }

    public static <T extends Number> T requireInClosedOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        NumberRangeValidator.validateInClosedOpenRange(value, lowerBound, upperBound);
        return value;
    }

    public static <T extends Number> T requireInOpenClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        NumberRangeValidator.validateInOpenClosedRange(value, lowerBound, upperBound);
        return value;
    }

    public static <T extends Number> T requireInOpenClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        NumberRangeValidator.validateInOpenClosedRange(value, lowerBound, upperBound);
        return value;
    }

    public static <T extends Number & Comparable<T>> T requireInAnyClosedRanges(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound,
            final String name
    ) {
        NumberRangeValidator.validateInAnyClosedRanges(
                value,
                firstLowerBound,
                firstUpperBound,
                secondLowerBound,
                secondUpperBound,
                name
        );
        return value;
    }

    public static <T extends Number & Comparable<T>> T requireInAnyClosedRanges(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound
    ) {
        NumberRangeValidator.validateInAnyClosedRanges(
                value,
                firstLowerBound,
                firstUpperBound,
                secondLowerBound,
                secondUpperBound
        );
        return value;
    }

}
