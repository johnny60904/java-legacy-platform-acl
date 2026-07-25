package com.dxlan.acl.features.shared.numeric;

import java.util.Set;

public final class NumericLeadingDigitGuard {

    private NumericLeadingDigitGuard() {
        throw new AssertionError();
    }

    public static <T extends Number> T requireFirstDigitOne(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitOne(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitOne(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitOne(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitOne(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitOne(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitOne(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitOne();
        return value;
    }

    public static <T extends Number> T requireFirstDigitTwo(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitTwo(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitTwo(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitTwo(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitTwo(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitTwo(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitTwo(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitTwo();
        return value;
    }

    public static <T extends Number> T requireFirstDigitThree(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitThree(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitThree(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitThree(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitThree(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitThree(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitThree(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitThree();
        return value;
    }

    public static <T extends Number> T requireFirstDigitFour(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFour(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFour(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFour(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFour(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFour(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFour(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFour();
        return value;
    }

    public static <T extends Number> T requireFirstDigitFive(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFive(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFive(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFive(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFive(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFive(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitFive(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitFive();
        return value;
    }

    public static <T extends Number> T requireFirstDigitSix(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSix(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSix(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSix(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSix(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSix(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSix(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSix();
        return value;
    }

    public static <T extends Number> T requireFirstDigitSeven(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSeven(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSeven(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSeven(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSeven(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSeven(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitSeven(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitSeven();
        return value;
    }

    public static <T extends Number> T requireFirstDigitEight(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitEight(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitEight(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitEight(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitEight(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitEight(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitEight(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitEight();
        return value;
    }

    public static <T extends Number> T requireFirstDigitNine(
            final T value,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitNine(strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitNine(
            final T value,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitNine(strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitNine(
            final T value,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitNine(name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitNine(
            final T value
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigitNine();
        return value;
    }

    public static <T extends Number> T requireFirstDigit(
            final T value,
            final int requiredDigitCount,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigit(requiredDigitCount, strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigit(
            final T value,
            final int requiredDigitCount,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigit(requiredDigitCount, strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigit(
            final T value,
            final int requiredDigitCount,
            final String name
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigit(requiredDigitCount, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigit(
            final T value,
            final int requiredDigitCount
    ) {
        NumericLeadingDigitValidator.of(value).validateFirstDigit(requiredDigitCount);
        return value;
    }

    public static <T extends Number> T requireFirstDigitWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit, strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit, strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitWithin(
            final T value,
            final int requiredMinDigit,
            final int requiredMaxDigit
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(requiredMinDigit, requiredMaxDigit);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final LeadingDigitStrategy strategy,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, strategy, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final LeadingDigitStrategy strategy
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, strategy);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final String name
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts, name);
        return value;
    }

    public static <T extends Number> T requireFirstDigitAnyOf(
            final T value,
            final int[] allowedDigitCounts
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedDigitCounts);
        return value;
    }

}
