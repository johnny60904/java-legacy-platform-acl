package com.dxlan.acl.features.shared.numeric;

import java.util.Objects;

public final class NumberRangeValidator {

    private static final String DEFAULT_NAME = "Value";

    private NumberRangeValidator() {
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

    public static void validateNotNull(
            final Object target,
            final String name
    ) {
        Objects.requireNonNull(target, name + " must not be null.");
    }

    private static <T extends Number> void validateRangeDefinitionValid(
            final T lowerBound,
            final T upperBound
    ) {
        validateNotNull(lowerBound, "LowerBound");
        validateNotNull(upperBound, "UpperBound");
        if (
                (NumberComparator.compare(lowerBound, upperBound) >= 0) ||
                (NumberPredicate.signum(lowerBound) < 0)
        ) {
            throw new IllegalArgumentException(
                    "Invalid range definition: lower / minimum bound '" +
                    lowerBound + "' cannot be greater than or equal to upper / maximum bound '" +
                    upperBound + "', or less than zero (lower / minimum bound < 0)."
            );
        }
    }

    private static <T extends Number> void validateInputs(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        validateNameHasText(name);
        validateNotNull(value, name);
        validateRangeDefinitionValid(lowerBound, upperBound);
    }

    private static <T extends Number> void validateInputs(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound,
            final String name
    ) {
        validateNameHasText(name);
        validateNotNull(value, name);
        validateRangeDefinitionValid(firstLowerBound, firstUpperBound);
        validateRangeDefinitionValid(secondLowerBound, secondUpperBound);
    }

    public static <T extends Number> void validateInClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        validateInputs(value, lowerBound, upperBound, name);
        if (
                (NumberComparator.compare(value, lowerBound) < 0) ||
                (NumberComparator.compare(value, upperBound) > 0)
        ) {
            throw new IllegalArgumentException(
                    name + " must be within closed range [" +
                    lowerBound + ", " + upperBound + "], but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateInClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        validateInClosedRange(value, lowerBound, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateInOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        validateInputs(value, lowerBound, upperBound, name);
        if (
                (NumberComparator.compare(value, lowerBound) <= 0) ||
                (NumberComparator.compare(value, upperBound) >= 0)
        ) {
            throw new IllegalArgumentException(
                    name + " must be within open range (" +
                    lowerBound + ", " + upperBound + "), but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateInOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        validateInOpenRange(value, lowerBound, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateInClosedOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        validateInputs(value, lowerBound, upperBound, name);
        if (
                (NumberComparator.compare(value, lowerBound) < 0) ||
                (NumberComparator.compare(value, upperBound) >= 0)
        ) {
            throw new IllegalArgumentException(
                    name + " must be within closed-open range [" +
                    lowerBound + ", " + upperBound + "), but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateInClosedOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        validateInClosedOpenRange(value, lowerBound, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number> void validateInOpenClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final String name
    ) {
        validateInputs(value, lowerBound, upperBound, name);
        if (
                (NumberComparator.compare(value, lowerBound) <= 0) ||
                (NumberComparator.compare(value, upperBound) > 0)
        ) {
            throw new IllegalArgumentException(
                    name + " must be within open-closed range (" +
                    lowerBound + ", " + upperBound + "], but was " + value + "."
            );
        }
    }

    public static <T extends Number> void validateInOpenClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound
    ) {
        validateInOpenClosedRange(value, lowerBound, upperBound, DEFAULT_NAME);
    }

    public static <T extends Number & Comparable<T>> T validateInAnyClosedRanges(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound,
            final String name
    ) {
        validateInputs(
                value,
                firstLowerBound,
                firstUpperBound,
                secondLowerBound,
                secondUpperBound,
                name
        );
        boolean valid =
                (
                        NumberComparator.compare(value, firstLowerBound) >= 0 &&
                        NumberComparator.compare(value, firstUpperBound) <= 0
                ) ||
                (
                        NumberComparator.compare(value, secondLowerBound) >= 0 &&
                        NumberComparator.compare(value, secondUpperBound) <= 0
                );
        if (!valid) {
            throw new IllegalArgumentException(
                    name + " must be within either valid closed ranges: " +
                    "[" + firstLowerBound + ", " + firstUpperBound + "] or " +
                    "[" + secondLowerBound + ", " + secondUpperBound + "]" +
                    ", but was " + value + "."
            );
        }
        return value;
    }

    public static <T extends Number & Comparable<T>> T validateInAnyClosedRanges(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound
    ) {
        return validateInAnyClosedRanges(
                value,
                firstLowerBound,
                firstUpperBound,
                secondLowerBound,
                secondUpperBound,
                DEFAULT_NAME
        );
    }

}
