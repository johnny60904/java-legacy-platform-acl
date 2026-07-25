package com.dxlan.acl.features.shared.numeric;

import java.util.Set;

public final class NumericDigitCountGuard {

    private NumericDigitCountGuard() {
        throw new AssertionError();
    }

    public static <T extends Number> T requireOnesDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateOnesDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireOnesDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateOnesDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireOnesDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateOnesDigit(name);
        return value;
    }

    public static <T extends Number> T requireOnesDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateOnesDigit();
        return value;
    }

    public static <T extends Number> T requireTensDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTensDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T validateTensDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateTensDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireTensDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTensDigit(name);
        return value;
    }

    public static <T extends Number> T requireTensDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateTensDigit();
        return value;
    }

    public static <T extends Number> T requireHundredsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireHundredsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateHundredsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireHundredsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredsDigit(name);
        return value;
    }

    public static <T extends Number> T requireHundredsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateHundredsDigit();
        return value;
    }

    public static <T extends Number> T requireThousandsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateThousandsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireThousandsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateThousandsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireThousandsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateThousandsDigit(name);
        return value;
    }

    public static <T extends Number> T requireThousandsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateThousandsDigit();
        return value;
    }

    public static <T extends Number> T requireTenThousandsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTenThousandsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireTenThousandsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateTenThousandsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireTenThousandsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTenThousandsDigit(name);
        return value;
    }

    public static <T extends Number> T requireTenThousandsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateTenThousandsDigit();
        return value;
    }

    public static <T extends Number> T requireHundredThousandsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredThousandsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireHundredThousandsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateHundredThousandsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireHundredThousandsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredThousandsDigit(name);
        return value;
    }

    public static <T extends Number> T requireHundredThousandsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateHundredThousandsDigit();
        return value;
    }

    public static <T extends Number> T requireMillionsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateMillionsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireMillionsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateMillionsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireMillionsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateMillionsDigit(name);
        return value;
    }

    public static <T extends Number> T requireMillionsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateMillionsDigit();
        return value;
    }

    public static <T extends Number> T requireTenMillionsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTenMillionsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireTenMillionsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateTenMillionsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireTenMillionsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateTenMillionsDigit(name);
        return value;
    }

    public static <T extends Number> T requireTenMillionsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateTenMillionsDigit();
        return value;
    }

    public static <T extends Number> T requireHundredMillionsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredMillionsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireHundredMillionsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateHundredMillionsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireHundredMillionsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateHundredMillionsDigit(name);
        return value;
    }

    public static <T extends Number> T requireHundredMillionsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateHundredMillionsDigit();
        return value;
    }

    public static <T extends Number> T requireBillionsDigit(
            final T value,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateBillionsDigit(strategy, name);
        return value;
    }

    public static <T extends Number> T requireBillionsDigit(
            final T value,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateBillionsDigit(strategy);
        return value;
    }

    public static <T extends Number> T requireBillionsDigit(
            final T value,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateBillionsDigit(name);
        return value;
    }

    public static <T extends Number> T requireBillionsDigit(
            final T value
    ) {
        NumericDigitCountValidator.of(value).validateBillionsDigit();
        return value;
    }

    public static <T extends Number> T requireDigitCount(
            final T value,
            final int requiredDigitCount,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateDigitCount(requiredDigitCount, strategy, name);
        return value;
    }

    public static <T extends Number> T requireDigitCount(
            final T value,
            final int requiredDigitCount,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator.of(value).validateDigitCount(requiredDigitCount, strategy);
        return value;
    }

    public static <T extends Number> T requireDigitCount(
            final T value,
            final int requiredDigitCount,
            final String name
    ) {
        NumericDigitCountValidator.of(value).validateDigitCount(requiredDigitCount, name);
        return value;
    }

    public static <T extends Number> T requireDigitCount(
            final T value,
            final int requiredDigitCount
    ) {
        NumericDigitCountValidator.of(value).validateDigitCount(requiredDigitCount);
        return value;
    }

    public static <T extends Number> T requireDigitCountWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(requiredMinDigit, requiredMaxDigit, strategy, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(requiredMinDigit, requiredMaxDigit, strategy);
        return value;
    }

    public static <T extends Number> T requireDigitCountWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(requiredMinDigit, requiredMaxDigit, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(requiredMinDigit, requiredMaxDigit);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final DigitStrategy strategy,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final DigitStrategy strategy
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final String name
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, name);
        return value;
    }

    public static <T extends Number> T requireDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts);
        return value;
    }

}
