package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.arrays.ArrayJoiner;
import com.dxlan.acl.features.shared.arrays.ArrayLengthSecurity;
import com.dxlan.acl.features.shared.arrays.ArrayValidator;
import com.dxlan.acl.features.shared.collections.CollectionValidator;
import com.dxlan.acl.features.shared.collections.SetJoiner;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class NumericLeadingDigitValidator {

    private static final String DEFAULT_NAME = "Value";

    private static final int FIRST_DIGIT_ONE = 1;
    private static final int FIRST_DIGIT_TWO = 2;
    private static final int FIRST_DIGIT_THREE = 3;
    private static final int FIRST_DIGIT_FOUR = 4;
    private static final int FIRST_DIGIT_FIVE = 5;
    private static final int FIRST_DIGIT_SIX = 6;
    private static final int FIRST_DIGIT_SEVEN = 7;
    private static final int FIRST_DIGIT_EIGHT = 8;
    private static final int FIRST_DIGIT_NINE = 9;

    private final NumericLeadingDigitMeasurer measurer;

    private NumericLeadingDigitValidator(
            final Number value
    ) {
        Objects.requireNonNull(value, "Value must not be null.");
        this.measurer = NumericLeadingDigitMeasurer.of(value);
    }

    private static void throwWhenSpecificMismatched(
            final int mismatchedDigit,
            final int requiredDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must contain a leading " +
                strategy.displayName() +
                " digit of " + requiredDigit + "digits, but was: " +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwWhenSpecificMismatched(
            final int mismatchedDigit,
            final int requiredDigit,
            final LeadingDigitStrategy strategy
    ) {
        throwWhenSpecificMismatched(mismatchedDigit, requiredDigit, strategy, DEFAULT_NAME);
    }

    private static void throwWhenOutOfRange(
            final int mismatchedDigit,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must contain a leading" +
                strategy.displayName() +
                " digit between '" +
                requiredMinDigit + "' and '" +
                requiredMaxDigit + "' (inclusive), but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwWhenOutOfRange(
            final int mismatchedDigit,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy
    ) {
        throwWhenOutOfRange(
                mismatchedDigit, requiredMinDigit, requiredMaxDigit,
                strategy, DEFAULT_NAME
        );
    }

    private static void throwWhenDiscreteDisallowed(
            final int mismatchedDigit,
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must start with one of the allowed leading " +
                strategy.displayName() +
                " digits: " +
                "[" + SetJoiner.join(allowedDigits) + "]" +
                ", but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwWhenDiscreteDisallowed(
            final int mismatchedDigit,
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        throwWhenDiscreteDisallowed(mismatchedDigit, allowedDigits, strategy, DEFAULT_NAME);
    }

    private static void throwWhenDiscreteDisallowed(
            final int mismatchedDigit,
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must start with one of the allowed leading " +
                strategy.displayName() +
                " digits: " +
                "[" + ArrayJoiner.join(allowedDigits) + "]" +
                ", but was: '" +
                "'" + mismatchedDigit + "'."
        );
    }

    private static void throwWhenDiscreteDisallowed(
            final int mismatchedDigit,
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        throwWhenDiscreteDisallowed(mismatchedDigit, allowedDigits, strategy, DEFAULT_NAME);
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
            final LeadingDigitStrategy strategy
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

    private void validateSpecifiedFirstDigit(
            final int requiredFirstDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        int firstDigit = measurer.getFirstDigit(strategy);
        if (firstDigit != requiredFirstDigit) {
            throwWhenSpecificMismatched(
                    firstDigit,
                    requiredFirstDigit,
                    strategy,
                    name
            );
        }
    }

    private void validateSpecifiedFirstDigit(
            final int requiredFirstDigit,
            final String name
    ) {
        validateNameHasText(name);
        int firstDigit = measurer.getFirstDigit();
        if (firstDigit != requiredFirstDigit) {
            throwWhenSpecificMismatched(
                    firstDigit,
                    requiredFirstDigit,
                    NumericLeadingDigitMeasurer.DEFAULT_STRATEGY,
                    name
            );
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        validateRangeDefinitionValid(lowerBound, upperBound);
        int firstDigit = measurer.getFirstDigit(strategy);
        if (firstDigit < lowerBound || firstDigit > upperBound) {
            throwWhenOutOfRange(firstDigit, lowerBound, upperBound, strategy, name);
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound,
            final LeadingDigitStrategy strategy
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
        int firstDigit = measurer.getFirstDigit();
        if (firstDigit < lowerBound || firstDigit > upperBound) {
            throwWhenOutOfRange(
                    firstDigit, lowerBound, upperBound,
                    NumericLeadingDigitMeasurer.DEFAULT_STRATEGY, name
            );
        }
    }

    private void validateWithinRange(
            final int lowerBound,
            final int upperBound
    ) {
        validateWithinRange(lowerBound, upperBound, DEFAULT_NAME);
    }

    private void validateTargetFirstDigitAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        CollectionValidator.validateNoneNull(allowedDigits, "AllowedDigits");
        CollectionValidator.validateSizeBelowSecurityBound(allowedDigits, "AllowedDigits");
        int firstDigit = measurer.getFirstDigit(strategy);
        if (!allowedDigits.contains(firstDigit)) {
            throwWhenDiscreteDisallowed(firstDigit, allowedDigits, strategy, name);
        }
    }

    private void validateTargetFirstDigitAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateNameHasText(name);
        validateStrategyNotNull(strategy);
        ArrayValidator.validateNotEmpty(allowedDigits, "AllowedDigits");
        ArrayValidator.validateLengthBelowSecurityBound(allowedDigits, "AllowedDigits");
        int firstDigit = measurer.getFirstDigit(strategy);
        if (allowedDigits.length <= ArrayLengthSecurity.THRESHOLD) {
            for (int allowedDigit : allowedDigits) if (allowedDigit == firstDigit) return;
            throwWhenDiscreteDisallowed(firstDigit, allowedDigits, strategy, name);
        }
        Arrays.sort(allowedDigits);
        if (Arrays.binarySearch(allowedDigits, firstDigit) < 0) {
            throwWhenDiscreteDisallowed(firstDigit, allowedDigits, strategy, name);
        }
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateTargetFirstDigitAnyOf(allowedDigits, strategy, name);
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateTargetFirstDigitAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits,
            final String name
    ) {
        validateTargetFirstDigitAnyOf(
                allowedDigits,
                NumericLeadingDigitMeasurer.DEFAULT_STRATEGY,
                name
        );
    }

    private void validateAnyOf(
            final Set<Integer> allowedDigits
    ) {
        validateTargetFirstDigitAnyOf(
                allowedDigits,
                NumericLeadingDigitMeasurer.DEFAULT_STRATEGY,
                DEFAULT_NAME
        );
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateTargetFirstDigitAnyOf(allowedDigits, strategy, name);
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateTargetFirstDigitAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    private void validateAnyOf(
            final int[] allowedDigits,
            final String name
    ) {
        validateTargetFirstDigitAnyOf(
                allowedDigits,
                NumericLeadingDigitMeasurer.DEFAULT_STRATEGY,
                name
        );
    }

    private void validateAnyOf(
            final int[] allowedDigits
    ) {
        validateTargetFirstDigitAnyOf(
                allowedDigits,
                NumericLeadingDigitMeasurer.DEFAULT_STRATEGY,
                DEFAULT_NAME
        );
    }

    public void validateFirstDigitOne(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_ONE, strategy, name);
    }

    public void validateFirstDigitOne(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitOne(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitOne(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_ONE, name);
    }

    public void validateFirstDigitOne() {
        validateFirstDigitOne(DEFAULT_NAME);
    }

    public void validateFirstDigitTwo(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_TWO, strategy, name);
    }

    public void validateFirstDigitTwo(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitTwo(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitTwo(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_TWO, name);
    }

    public void validateFirstDigitTwo() {
        validateFirstDigitTwo(DEFAULT_NAME);
    }

    public void validateFirstDigitThree(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_THREE, strategy, name);
    }

    public void validateFirstDigitThree(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitThree(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitThree(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_THREE, name);
    }

    public void validateFirstDigitThree() {
        validateFirstDigitThree(DEFAULT_NAME);
    }

    public void validateFirstDigitFour(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_FOUR, strategy, name);
    }

    public void validateFirstDigitFour(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitFour(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitFour(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_FOUR, name);
    }

    public void validateFirstDigitFour() {
        validateFirstDigitFour(DEFAULT_NAME);
    }

    public void validateFirstDigitFive(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_FIVE, strategy, name);
    }

    public void validateFirstDigitFive(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitFive(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitFive(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_FIVE, name);
    }

    public void validateFirstDigitFive() {
        validateFirstDigitFive(DEFAULT_NAME);
    }

    public void validateFirstDigitSix(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_SIX, strategy, name);
    }

    public void validateFirstDigitSix(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitSix(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitSix(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_SIX, name);
    }

    public void validateFirstDigitSix() {
        validateFirstDigitSix(DEFAULT_NAME);
    }

    public void validateFirstDigitSeven(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_SEVEN, strategy, name);
    }

    public void validateFirstDigitSeven(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitSeven(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitSeven(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_SEVEN, name);
    }

    public void validateFirstDigitSeven() {
        validateFirstDigitSeven(DEFAULT_NAME);
    }

    public void validateFirstDigitEight(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_EIGHT, strategy, name);
    }

    public void validateFirstDigitEight(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitEight(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitEight(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_EIGHT, name);
    }

    public void validateFirstDigitEight() {
        validateFirstDigitEight(DEFAULT_NAME);
    }

    public void validateFirstDigitNine(
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_NINE, strategy, name);
    }

    public void validateFirstDigitNine(
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitNine(strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitNine(
            final String name
    ) {
        validateSpecifiedFirstDigit(FIRST_DIGIT_NINE, name);
    }

    public void validateFirstDigitNine() {
        validateFirstDigitNine(DEFAULT_NAME);
    }

    public void validateFirstDigit(
            final int requiredFirstDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateSpecifiedFirstDigit(requiredFirstDigit, strategy, name);
    }

    public void validateFirstDigit(
            final int requiredFirstDigit,
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigit(requiredFirstDigit, strategy, DEFAULT_NAME);
    }

    public void validateFirstDigit(
            final int requiredFirstDigit,
            final String name
    ) {
        validateSpecifiedFirstDigit(requiredFirstDigit, name);
    }

    public void validateFirstDigit(
            final int requiredFirstDigit
    ) {
        validateFirstDigit(requiredFirstDigit, DEFAULT_NAME);
    }

    public void validateFirstDigitWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateWithinRange(requiredMinDigit, requiredMaxDigit, strategy, name);
    }

    public void validateFirstDigitWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy
    ) {
        validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit, strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final String name
    ) {
        validateWithinRange(requiredMinDigit, requiredMaxDigit, name);
    }

    public void validateFirstDigitWithin(
            final int requiredMinDigit,
            final int requiredMaxDigit
    ) {
        validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit, DEFAULT_NAME);
    }

    public void validateFirstDigitAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateAnyOf(allowedDigits, strategy, name);
    }

    public void validateFirstDigitAnyOf(
            final Set<Integer> allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitAnyOf(
            final Set<Integer> allowedDigits,
            final String name
    ) {
        validateAnyOf(allowedDigits, name);
    }

    public void validateFirstDigitAnyOf(
            final Set<Integer> allowedDigits
    ) {
        validateAnyOf(allowedDigits);
    }

    public void validateFirstDigitAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        validateAnyOf(allowedDigits, strategy, name);
    }

    public void validateFirstDigitAnyOf(
            final int[] allowedDigits,
            final LeadingDigitStrategy strategy
    ) {
        validateAnyOf(allowedDigits, strategy, DEFAULT_NAME);
    }

    public void validateFirstDigitAnyOf(
            final int[] allowedDigits,
            final String name
    ) {
        validateAnyOf(allowedDigits, name);
    }

    public void validateFirstDigitAnyOf(
            final int[] allowedDigits
    ) {
        validateAnyOf(allowedDigits);
    }

    public static NumericLeadingDigitValidator of(
            final Number value
    ) {
        return new NumericLeadingDigitValidator(value);
    }

}
