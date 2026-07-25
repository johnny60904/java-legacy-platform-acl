package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.arrays.ArrayJoiner;
import com.dxlan.acl.features.shared.arrays.ArrayLengthSecurity;
import com.dxlan.acl.features.shared.arrays.ArrayValidator;
import com.dxlan.acl.features.shared.collections.CollectionValidator;
import com.dxlan.acl.features.shared.collections.SetJoiner;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class NumericDigitCountValidator {

    private static final String DEFAULT_NAME = "Value";

    private static final int ONES_DIGIT = 1;
    private static final int TENS_DIGIT = 2;
    private static final int HUNDREDS_DIGIT = 3;
    private static final int THOUSANDS_DIGIT = 4;
    private static final int TEN_THOUSANDS_DIGIT = 5;
    private static final int HUNDRED_THOUSANDS_DIGIT = 6;
    private static final int MILLIONS_DIGIT = 7;
    private static final int TEN_MILLIONS_DIGIT = 8;
    private static final int HUNDRED_MILLIONS_DIGIT = 9;
    private static final int BILLIONS_DIGIT = 10;

    private final NumericDigitCountMeasurer measurer;

    private NumericDigitCountValidator(
            final Number value
    ) {
        Objects.requireNonNull(value, "Value must not be null.");
        this.measurer = NumericDigitCountMeasurer.of(value);
    }

    private static void throwMismatched(
            final int mismatchedDigit,
            final int requiredDigit,
            final DigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must be an exact " +
                strategy.displayName() +
                " of " + requiredDigit + "digits, but was: " +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwMismatched(
            final int mismatchedDigit,
            final int requiredDigit,
            final DigitStrategy strategy
    ) {
        throwMismatched(mismatchedDigit, requiredDigit, strategy, DEFAULT_NAME);
    }

    private static void throwOutOfRange(
            final int mismatchedDigit,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must have a / an " +
                strategy.displayName() +
                " digit count between '" +
                requiredMinDigit + "' and '" +
                requiredMaxDigit + "' digits (inclusive), but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwOutOfRange(
            final int mismatchedDigit,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy
    ) {
        throwOutOfRange(
                mismatchedDigit, requiredMinDigit, requiredMaxDigit,
                strategy, DEFAULT_NAME
        );
    }

    private static void throwDisallowed(
            final int mismatchedDigit,
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must have a / an " +
                strategy.displayName() +
                " digit count matching one of the allowed values: " +
                "[" + SetJoiner.join(allowedDigits) + "]" +
                ", but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwDisallowed(
            final int mismatchedDigit,
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy
    ) {
        throwDisallowed(mismatchedDigit, allowedDigits, strategy, DEFAULT_NAME);
    }

    private static void throwDisallowed(
            final int mismatchedDigit,
            final int[] allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must have a / an " +
                strategy.displayName() +
                " digit count matching one of the allowed values: " +
                "[" + ArrayJoiner.join(allowedDigits) + "]" +
                ", but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwDisallowed(
            final int mismatchedDigit,
            final int[] allowedDigits,
            final DigitStrategy strategy
    ) {
        throwDisallowed(mismatchedDigit, allowedDigits, strategy, DEFAULT_NAME);
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
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

    private void validateTargetDigitCount(
            final int requiredDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        int digitCount = measurer.count(strategy);
        if (digitCount != requiredDigits) {
            throwMismatched(
                    digitCount,
                    requiredDigits,
                    strategy,
                    name
            );
        }
    }

    private void validateTargetDigitCount(
            final int requiredDigits,
            final String name
    ) {
        validateNameHasText(name);
        int digitCount = measurer.count();
        if (digitCount != requiredDigits) {
            throwMismatched(
                    digitCount,
                    requiredDigits,
                    NumericDigitCountMeasurer.DEFAULT_STRATEGY,
                    name
            );
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound,
            final DigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        validateRangeDefinitionValid(lowerBound, upperBound);
        int digitCount = measurer.count(strategy);
        if (digitCount < lowerBound || digitCount > upperBound) {
            throwOutOfRange(digitCount, lowerBound, upperBound, strategy, name);
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound,
            final DigitStrategy strategy
    ) {
        validateWithinRange(lowerBound, upperBound, strategy, DEFAULT_NAME);
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound,
            final String name
    ) {
        validateNameHasText(name);
        validateRangeDefinitionValid(lowerBound, upperBound);
        int digitCount = measurer.count();
        if (digitCount < lowerBound || digitCount > upperBound) {
            throwOutOfRange(
                    digitCount, lowerBound, upperBound,
                    NumericDigitCountMeasurer.DEFAULT_STRATEGY, name
            );
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound
    ) {
        validateWithinRange(lowerBound, upperBound, DEFAULT_NAME);
    }

    private void validateTargetDigitCountAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        CollectionValidator.validateNoneNull(allowedDigits, "AllowedDigits");
        CollectionValidator.validateSizeBelowSecurityBound(allowedDigits, "AllowedDigits");
        int digitCount = measurer.count(strategy);
        if (!allowedDigits.contains(digitCount)) {
            throwDisallowed(digitCount, allowedDigits, strategy, name);
        }
    }

    private void validateTargetDigitCountAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        ArrayValidator.validateNotEmpty(allowedDigits, "AllowedDigits");
        ArrayValidator.validateLengthBelowSecurityBound(allowedDigits, "AllowedDigits");
        int digitCount = measurer.count(strategy);
        if (allowedDigits.length <= ArrayLengthSecurity.THRESHOLD) {
            for (int allowedDigit : allowedDigits) if (allowedDigit == digitCount) return;
            throwDisallowed(digitCount, allowedDigits, strategy, name);
        }
        Arrays.sort(allowedDigits);
        if (Arrays.binarySearch(allowedDigits, digitCount) < 0) {
            throwDisallowed(digitCount, allowedDigits, strategy, name);
        }
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCountAnyOf(allowedDigits, strategy, name);
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy
    ) {
        validateTargetDigitCountAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final String name
    ) {
        validateTargetDigitCountAnyOf(
                allowedDigits,
                NumericDigitCountMeasurer.DEFAULT_STRATEGY,
                name
        );
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits
    ) {
        validateTargetDigitCountAnyOf(
                allowedDigits,
                NumericDigitCountMeasurer.DEFAULT_STRATEGY,
                DEFAULT_NAME
        );
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCountAnyOf(allowedDigits, strategy, name);
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy
    ) {
        validateTargetDigitCountAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final String name
    ) {
        validateTargetDigitCountAnyOf(
                allowedDigits,
                NumericDigitCountMeasurer.DEFAULT_STRATEGY,
                name
        );
    }

    private void validateAnyOf(
            final int[] allowedDigits
    ) {
        validateTargetDigitCountAnyOf(
                allowedDigits,
                NumericDigitCountMeasurer.DEFAULT_STRATEGY,
                DEFAULT_NAME
        );
    }

    public void validateOnesDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(ONES_DIGIT, strategy, name);
    }

    public void validateOnesDigit(
            final DigitStrategy strategy
    ) {
        validateOnesDigit(strategy, DEFAULT_NAME);
    }

    public void validateOnesDigit(
            final String name
    ) {
        validateTargetDigitCount(ONES_DIGIT, name);
    }

    public void validateOnesDigit() {
        validateOnesDigit(DEFAULT_NAME);
    }

    public void validateTensDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(TENS_DIGIT, strategy, name);
    }

    public void validateTensDigit(
            final DigitStrategy strategy
    ) {
        validateTensDigit(strategy, DEFAULT_NAME);
    }

    public void validateTensDigit(
            final String name
    ) {
        validateTargetDigitCount(TENS_DIGIT, name);
    }

    public void validateTensDigit() {
        validateTensDigit(DEFAULT_NAME);
    }

    public void validateHundredsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(HUNDREDS_DIGIT, strategy, name);
    }

    public void validateHundredsDigit(
            final DigitStrategy strategy
    ) {
        validateHundredsDigit(strategy, DEFAULT_NAME);
    }

    public void validateHundredsDigit(
            final String name
    ) {
        validateTargetDigitCount(HUNDREDS_DIGIT, name);
    }

    public void validateHundredsDigit() {
        validateHundredsDigit(DEFAULT_NAME);
    }

    public void validateThousandsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(THOUSANDS_DIGIT, strategy, name);
    }

    public void validateThousandsDigit(
            final DigitStrategy strategy
    ) {
        validateThousandsDigit(strategy, DEFAULT_NAME);
    }

    public void validateThousandsDigit(
            final String name
    ) {
        validateTargetDigitCount(THOUSANDS_DIGIT, name);
    }

    public void validateThousandsDigit() {
        validateThousandsDigit(DEFAULT_NAME);
    }

    public void validateTenThousandsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(TEN_THOUSANDS_DIGIT, strategy, name);
    }

    public void validateTenThousandsDigit(
            final DigitStrategy strategy
    ) {
        validateTenThousandsDigit(strategy, DEFAULT_NAME);
    }

    public void validateTenThousandsDigit(
            final String name
    ) {
        validateTargetDigitCount(TEN_THOUSANDS_DIGIT, name);
    }

    public void validateTenThousandsDigit() {
        validateTenThousandsDigit(DEFAULT_NAME);
    }

    public void validateHundredThousandsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(HUNDRED_THOUSANDS_DIGIT, strategy, name);
    }

    public void validateHundredThousandsDigit(
            final DigitStrategy strategy
    ) {
        validateHundredThousandsDigit(strategy, DEFAULT_NAME);
    }

    public void validateHundredThousandsDigit(
            final String name
    ) {
        validateTargetDigitCount(HUNDRED_THOUSANDS_DIGIT, name);
    }

    public void validateHundredThousandsDigit() {
        validateHundredThousandsDigit(DEFAULT_NAME);
    }

    public void validateMillionsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(MILLIONS_DIGIT, strategy, name);
    }

    public void validateMillionsDigit(
            final DigitStrategy strategy
    ) {
        validateMillionsDigit(strategy, DEFAULT_NAME);
    }

    public void validateMillionsDigit(
            final String name
    ) {
        validateTargetDigitCount(MILLIONS_DIGIT, name);
    }

    public void validateMillionsDigit() {
        validateMillionsDigit(DEFAULT_NAME);
    }

    public void validateTenMillionsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(TEN_MILLIONS_DIGIT, strategy, name);
    }

    public void validateTenMillionsDigit(
            final DigitStrategy strategy
    ) {
        validateTenMillionsDigit(strategy, DEFAULT_NAME);
    }

    public void validateTenMillionsDigit(
            final String name
    ) {
        validateTargetDigitCount(TEN_MILLIONS_DIGIT, name);
    }

    public void validateTenMillionsDigit() {
        validateTenMillionsDigit(DEFAULT_NAME);
    }

    public void validateHundredMillionsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(HUNDRED_MILLIONS_DIGIT, strategy, name);
    }

    public void validateHundredMillionsDigit(
            final DigitStrategy strategy
    ) {
        validateHundredMillionsDigit(strategy, DEFAULT_NAME);
    }

    public void validateHundredMillionsDigit(
            final String name
    ) {
        validateTargetDigitCount(HUNDRED_MILLIONS_DIGIT, name);
    }

    public void validateHundredMillionsDigit() {
        validateHundredMillionsDigit(DEFAULT_NAME);
    }

    public void validateBillionsDigit(
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(BILLIONS_DIGIT, strategy, name);
    }

    public void validateBillionsDigit(
            final DigitStrategy strategy
    ) {
        validateBillionsDigit(strategy, DEFAULT_NAME);
    }

    public void validateBillionsDigit(
            final String name
    ) {
        validateTargetDigitCount(BILLIONS_DIGIT, name);
    }

    public void validateBillionsDigit() {
        validateBillionsDigit(DEFAULT_NAME);
    }

    public void validateDigitCount(
            final int requiredDigitCount,
            final DigitStrategy strategy,
            final String name
    ) {
        validateTargetDigitCount(requiredDigitCount, strategy, name);
    }

    public void validateDigitCount(
            final int requiredDigitCount,
            final DigitStrategy strategy
    ) {
        validateDigitCount(requiredDigitCount, strategy, DEFAULT_NAME);
    }

    public void validateDigitCount(
            final int requiredDigitCount,
            final String name
    ) {
        validateTargetDigitCount(requiredDigitCount, name);
    }

    public void validateDigitCount(
            final int requiredDigitCount
    ) {
        validateDigitCount(requiredDigitCount, DEFAULT_NAME);
    }

    public void validateDigitCountWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy,
            final String name
    ) {
        validateWithinRange(requiredMinDigit, requiredMaxDigit, strategy, name);
    }

    public void validateDigitCountWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy
    ) {
        validateDigitCountWithin(requiredMinDigit, requiredMaxDigit, strategy, DEFAULT_NAME);
    }

    public void validateDigitCountWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final String name
    ) {
        validateWithinRange(requiredMinDigit, requiredMaxDigit, name);
    }

    public void validateDigitCountWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit
    ) {
        validateDigitCountWithin(requiredMinDigit, requiredMaxDigit, DEFAULT_NAME);
    }

    public void validateDigitCountAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateAnyOf(allowedDigits, strategy, name);
    }

    public void validateDigitCountAnyOf(
            final Set<Integer> allowedDigits,
            final DigitStrategy strategy
    ) {
        validateAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    public void validateDigitCountAnyOf(
            final Set<Integer> allowedDigits,
            final String name
    ) {
        validateAnyOf(allowedDigits, name);
    }

    public void validateDigitCountAnyOf(
            final Set<Integer> allowedDigits
    ) {
        validateAnyOf(allowedDigits);
    }

    public void validateDigitCountAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy,
            final String name
    ) {
        validateAnyOf(allowedDigits, strategy, name);
    }

    public void validateDigitCountAnyOf(
            final int[] allowedDigits,
            final DigitStrategy strategy
    ) {
        validateAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    public void validateDigitCountAnyOf(
            final int[] allowedDigits,
            final String name
    ) {
        validateAnyOf(allowedDigits, name);
    }

    public void validateDigitCountAnyOf(
            final int[] allowedDigits
    ) {
        validateAnyOf(allowedDigits);
    }

    public static NumericDigitCountValidator of(
            final Number value
    ) {
        return new NumericDigitCountValidator(value);
    }

}
