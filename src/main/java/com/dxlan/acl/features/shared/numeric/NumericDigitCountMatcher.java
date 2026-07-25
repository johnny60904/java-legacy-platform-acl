package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.arrays.ArrayLengthSecurity;
import com.dxlan.acl.features.shared.arrays.ArrayValidator;
import com.dxlan.acl.features.shared.collections.CollectionValidator;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class NumericDigitCountMatcher {

    private final NumericDigitCountMeasurer measurer;

    private NumericDigitCountMatcher(
            final Number value
    ) {
        Objects.requireNonNull(value, "Value must not be null.");
        this.measurer = NumericDigitCountMeasurer.of(value);
    }

    private static void validateStrategyNotNull(
            final DigitStrategy strategy
    ) {
        Objects.requireNonNull(strategy, "Strategy must not be null.");
    }

    private static void validateRangeDefinitionValid(
            final int minDigit,
            final int maxDigit
    ) {
        if (minDigit >= maxDigit || minDigit <= 0) {
            throw new IllegalArgumentException(
                    "Invalid range definition: minimum allowed digit count '" +
                    minDigit + "' cannot be greater than or equal to maximum allowed digit count '" +
                    maxDigit + "', or less than or equal to zero (minimum allowed digit count <= 0). " +
                    "Minimum allowed digit count: " + minDigit +
                    "Maximum allowed digit count: " + maxDigit + "."
            );
        }
    }

    private static boolean isTargetDigitCountAnyOf(
            final int target,
            final Set<Integer> allowedDigits
    ) {
        CollectionValidator.validateNoneNull(allowedDigits, "AllowedDigits");
        CollectionValidator.validateSizeBelowSecurityBound(allowedDigits, "AllowedDigits");
        return allowedDigits.contains(target);
    }

    private boolean isTargetDigitCountAnyOf(
            final int target,
            final int[] allowedDigits
    ) {
        ArrayValidator.validateNotEmpty(allowedDigits, "AllowedDigits");
        ArrayValidator.validateLengthBelowSecurityBound(allowedDigits, "AllowedDigits");
        if (allowedDigits.length <= ArrayLengthSecurity.THRESHOLD) {
            for (int allowedDigit : allowedDigits) if (allowedDigit == target) return true;
            return false;
        }
        Arrays.sort(allowedDigits);
        return Arrays.binarySearch(allowedDigits, target) >= 0;
    }

    public boolean isSingleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 1;
    }

    public boolean isDoubleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 2;
    }

    public boolean isTripleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 3;
    }

    public boolean isQuadrupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 4;
    }

    public boolean isQuintupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 5;
    }

    public boolean isSextupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 6;
    }

    public boolean isSeptupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 7;
    }

    public boolean isOctupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 8;
    }

    public boolean isNonupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 9;
    }

    public boolean isDecupleDigit(
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.count(strategy) == 10;
    }

    public boolean isSingleDigit() {
        return measurer.count() == 1;
    }

    public boolean isDoubleDigit() {
        return measurer.count() == 2;
    }

    public boolean isTripleDigit() {
        return measurer.count() == 3;
    }

    public boolean isQuadrupleDigit() {
        return measurer.count() == 4;
    }

    public boolean isQuintupleDigit() {
        return measurer.count() == 5;
    }

    public boolean isSextupleDigit() {
        return measurer.count() == 6;
    }

    public boolean isSeptupleDigit() {
        return measurer.count() == 7;
    }

    public boolean isOctupleDigit() {
        return measurer.count() == 8;
    }

    public boolean isNonupleDigit() {
        return measurer.count() == 9;
    }

    public boolean isDecupleDigit() {
        return measurer.count() == 10;
    }

    public boolean isDigitCount(
            final int n,
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        if (n <= 0) throw new IllegalArgumentException("Invalid digit count: " + n + ".");
        return measurer.count(strategy) == n;
    }

    public boolean isDigitCount(
            final int n
    ) {
        if (n <= 0) throw new IllegalArgumentException("Invalid digit count: " + n + ".");
        return measurer.count() == n;
    }

    /// closed range
    public boolean isDigitCountWithin(
            final int minDigit,
            final int maxDigit,
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        validateRangeDefinitionValid(minDigit, maxDigit);
        int digitCount = measurer.count(strategy);
        return digitCount >= minDigit && digitCount <= maxDigit;
    }

    public boolean isDigitCountWithin(
            final int minDigit,
            final int maxDigit
    ) {
        validateRangeDefinitionValid(minDigit, maxDigit);
        int digitCount = measurer.count();
        return digitCount >= minDigit && digitCount <= maxDigit;
    }

    public boolean isDigitCountAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        int digitCount = measurer.count(strategy);
        return isTargetDigitCountAnyOf(digitCount, allowedDigits);
    }

    public boolean isDigitCountAnyOf(
            final Set<Integer> allowedDigits
    ) {
        int digitCount = measurer.count();
        return isTargetDigitCountAnyOf(digitCount, allowedDigits);
    }

    public boolean isDigitCountAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        int digitCount = measurer.count(strategy);
        return isTargetDigitCountAnyOf(digitCount, allowedDigits);
    }

    public boolean isDigitCountAnyOf(
            final int[] allowedDigits
    ) {
        int digitCount = measurer.count();
        return isTargetDigitCountAnyOf(digitCount, allowedDigits);
    }

    public static NumericDigitCountMatcher of(
            final Number value
    ) {
        return new NumericDigitCountMatcher(value);
    }

}
