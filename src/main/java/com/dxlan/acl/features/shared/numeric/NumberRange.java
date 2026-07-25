package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.common.NameDisplayable;

import java.util.Objects;

public final class NumberRange<T extends Number & Comparable<T>> {

    private final T lower;
    private final BoundType lowerType;
    private final T upper;
    private final BoundType upperType;

    private static <T extends Number & Comparable<T>> void validateRangeDefinitionValid(
            final T lower,
            final T upper
    ) {
        if (NumberComparator.compare(lower, upper) >= 0) {
            throw new IllegalArgumentException(
                    "Invalid range definition: lower bound (" +
                    lower + ") cannot be greater than or equal to upper bound (" +
                    upper + ")."
            );
        }
    }

    private NumberRange(
            final T lower,
            final BoundType lowerType,
            final T upper,
            final BoundType upperType
    ) {
        Objects.requireNonNull(lower, "Lower must not be null.");
        Objects.requireNonNull(upper, "Upper must not be null.");
        Objects.requireNonNull(lowerType, "LowerType must not be null.");
        Objects.requireNonNull(upperType, "UpperType must not be null.");
        validateRangeDefinitionValid(lower, upper);
        this.lower = lower;
        this.lowerType = lowerType;
        this.upper = upper;
        this.upperType = upperType;
    }

    private enum BoundType implements NameDisplayable {

        INCLUSIVE("Inclusive"),
        EXCLUSIVE("Exclusive");

        private final String displayName;

        private BoundType(
                final String displayName
        ) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() {
            return displayName;
        }

    }

    public static <T extends Number & Comparable<T>> NumberRange<T> closed(
            final T lower,
            final T upper
    ) {
        return new NumberRange<>(
                lower,
                BoundType.INCLUSIVE,
                upper,
                BoundType.INCLUSIVE
        );
    }

    public static <T extends Number & Comparable<T>> NumberRange<T> open(
            final T lower,
            final T upper
    ) {
        return new NumberRange<>(
                lower,
                BoundType.EXCLUSIVE,
                upper,
                BoundType.EXCLUSIVE
        );
    }

    public static <T extends Number & Comparable<T>> NumberRange<T> closedOpen(
            final T lower,
            final T upper
    ) {
        return new NumberRange<>(
                lower,
                BoundType.INCLUSIVE,
                upper,
                BoundType.EXCLUSIVE
        );
    }

    public static <T extends Number & Comparable<T>> NumberRange<T> openClosed(
            final T lower,
            final T upper
    ) {
        return new NumberRange<>(
                lower,
                BoundType.EXCLUSIVE,
                upper,
                BoundType.INCLUSIVE
        );
    }

    public boolean contains(
            final T value
    ) {
        if (value == null) return false;

        int lowerCheck = NumberComparator.compare(value, lower);
        if (lowerType == BoundType.INCLUSIVE && lowerCheck < 0) return false;
        if (lowerType == BoundType.EXCLUSIVE && lowerCheck <= 0) return false;

        int upperCheck = NumberComparator.compare(value, upper);
        if (upperType == BoundType.INCLUSIVE && upperCheck > 0) return false;
        if (upperType == BoundType.EXCLUSIVE && upperCheck >= 0) return false;

        return true;
    }

    @Override
    public String toString() {
        String left = (lowerType == BoundType.INCLUSIVE) ? "[" : "(";
        String right = (upperType == BoundType.INCLUSIVE) ? "]" : ")";
        return left + lower + ", " + upper + right;
    }

}