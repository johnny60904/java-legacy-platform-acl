package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.arrays.ArrayLengthSecurity;
import com.dxlan.acl.features.shared.arrays.ArrayValidator;
import com.dxlan.acl.features.shared.collections.CollectionValidator;
import com.dxlan.acl.features.shared.objects.ObjectValidator;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class NumericLeadingDigitMatcher {

    private final NumericLeadingDigitMeasurer measurer;

    private NumericLeadingDigitMatcher(
            final Number value
    ) {
        ObjectValidator.validateNotNull(value);
        this.measurer = NumericLeadingDigitMeasurer.of(value);
    }

    private static void validateStrategyNotNull(
            final LeadingDigitStrategy strategy
    ) {
        ObjectValidator.validateNotNull(strategy, "Strategy");
    }

    private static void validateRequiredDigitValid(
            final int requiredDigit
    ) {
        if (requiredDigit < 0 || requiredDigit > 9) {
            throw new IllegalArgumentException(
                    "Digit must be withing 0 to 9, but was: " + requiredDigit + "."
            );
        }
    }

    private static void validateRangeDefinitionValid(
            final int minDigit,
            final int maxDigit
    ) {
        if (minDigit >= maxDigit || minDigit < 0 || minDigit > 9) {
            throw new IllegalArgumentException(
                    "Invalid range definition: minimum allowed first digit '" +
                    minDigit + "' cannot be greater than or equal to maximum allowed first digit '" +
                    maxDigit + "', or less than zero (minimum allowed first digit < 0)" +
                    ", or greater than 9 (minimum allowed first digit > 9)." +
                    "Minimum allowed first digit: " + minDigit +
                    "Maximum allowed first digit: " + maxDigit + "."
            );
        }
    }

    private static boolean isTargetFirstDigitAnyOf(
            final int target,
            final Set<Integer> allowedDigits
    ) {
        CollectionValidator.validateNoneNull(allowedDigits, "AllowedDigits");
        CollectionValidator.validateSizeBelowSecurityBound(allowedDigits, "AllowedDigits");
        return allowedDigits.contains(target);
    }

    private boolean isTargetFirstDigitAnyOf(
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

    public boolean isFirstDigitZero(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 0;
    }

    public boolean isFirstDigitOne(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 1;
    }

    public boolean isFirstDigitTwo(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 2;
    }

    public boolean isFirstDigitThree(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 3;
    }

    public boolean isFirstDigitFour(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 4;
    }

    public boolean isFirstDigitFive(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 5;
    }

    public boolean isFirstDigitSix(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 6;
    }

    public boolean isFirstDigitSeven(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 7;
    }

    public boolean isFirstDigitEight(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 8;
    }

    public boolean isFirstDigitNine(
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        return measurer.getFirstDigit(strategy) == 9;
    }

    public boolean isFirstDigitZero() { return measurer.getFirstDigit() == 0; }

    public boolean isFirstDigitOne() { return measurer.getFirstDigit() == 1; }

    public boolean isFirstDigitTwo() { return measurer.getFirstDigit() == 2; }

    public boolean isFirstDigitThree() { return measurer.getFirstDigit() == 3; }

    public boolean isFirstDigitFour() { return measurer.getFirstDigit() == 4; }

    public boolean isFirstDigitFive() { return measurer.getFirstDigit() == 5; }

    public boolean isFirstDigitSix() { return measurer.getFirstDigit() == 6; }

    public boolean isFirstDigitSeven() { return measurer.getFirstDigit() == 7; }

    public boolean isFirstDigitEight() { return measurer.getFirstDigit() == 8; }

    public boolean isFirstDigitNine() { return measurer.getFirstDigit() == 9; }

    public boolean isFirstDigit(
            final int n,
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        validateRequiredDigitValid(n);
        return measurer.getFirstDigit(strategy) == n;
    }

    public boolean isFirstDigit(
            final int n
    ) {
        validateRequiredDigitValid(n);
        return measurer.getFirstDigit() == n;
    }

    public boolean isFirstDigitWithin(
            final int minDigit,
            final int maxDigit,
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        validateRangeDefinitionValid(minDigit, maxDigit);
        int firstDigit = measurer.getFirstDigit(strategy);
        return firstDigit >= minDigit && firstDigit <= maxDigit;
    }

    public boolean isFirstDigitWithin(
            final int minDigit,
            final int maxDigit
    ) {
        validateRangeDefinitionValid(minDigit, maxDigit);
        int firstDigit = measurer.getFirstDigit();
        return firstDigit >= minDigit && firstDigit <= maxDigit;
    }

    public boolean isFirstDigitAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        int firstDigit = measurer.getFirstDigit(strategy);
        return isTargetFirstDigitAnyOf(firstDigit, allowedDigits);
    }

    public boolean isFirstDigitAnyOf(
            final Set<Integer> allowedDigits
    ) {
        int firstDigit = measurer.getFirstDigit();
        return isTargetFirstDigitAnyOf(firstDigit, allowedDigits);
    }

    public boolean isFirstDigitAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateStrategyNotNull(strategy);
        int firstDigit = measurer.getFirstDigit(strategy);
        return isTargetFirstDigitAnyOf(firstDigit, allowedDigits);
    }

    public boolean isFirstDigitAnyOf(
            final int[] allowedDigits
    ) {
        int firstDigit = measurer.getFirstDigit();
        return isTargetFirstDigitAnyOf(firstDigit, allowedDigits);
    }

    public static NumericLeadingDigitMatcher of(
            final Number value
    ) {
        return new NumericLeadingDigitMatcher(value);
    }

}
