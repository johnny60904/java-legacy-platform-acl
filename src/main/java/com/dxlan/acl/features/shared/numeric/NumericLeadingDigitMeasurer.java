package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.objects.ObjectValidator;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumericLeadingDigitMeasurer {

    private final Number value;

    public static final LeadingDigitStrategy DEFAULT_STRATEGY = LeadingDigitStrategy.LITERAL;

    private NumericLeadingDigitMeasurer(
            final Number value
    ) {
        ObjectValidator.validateNotNull(value);
        this.value = value;
    }

    public static NumericLeadingDigitMeasurer of(
            final Number value
    ) {
        return new NumericLeadingDigitMeasurer(value);
    }

    private static int getDigitFromString(
            final String str,
            final LeadingDigitStrategy strategy
    ) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                if (strategy == LeadingDigitStrategy.LITERAL) return c - '0';
                if (c != '0') return c - '0';
            }
        }
        return 0;
    }

    private static int parseFloatingPointString(
            final String str,
            final LeadingDigitStrategy strategy
    ) {
        if (str.indexOf('E') != -1 || str.indexOf('e') != -1) {
            BigDecimal bd = new BigDecimal(str).stripTrailingZeros();
            if (strategy == LeadingDigitStrategy.LITERAL) {
                return (bd.scale() > bd.precision())
                        ? 0
                        : getDigitFromString(bd.abs().toPlainString(), strategy);
            } else {
                return str.charAt(0) - '0';
            }
        }
        return getDigitFromString(str, strategy);
    }

    private static int getBigIntegerDigitCount(
            final BigInteger bigInteger
    ) {
        if (bigInteger.signum() == 0) return 1;
        final double factor = 0.3010299956639812;
        int digits = (int) (bigInteger.bitLength() * factor) + 1;
        if (bigInteger.compareTo(BigInteger.TEN.pow(digits - 1)) < 0) digits--;
        return digits;
    }

    private int calculateFirstDigit(
            final LeadingDigitStrategy strategy
    ) {
        return switch (value) {
            case Byte b    -> NumericLookupTable.LongNumber.getFirstDigit(Math.abs(b.longValue()));
            case Short s   -> NumericLookupTable.LongNumber.getFirstDigit(Math.abs(s.longValue()));
            case Integer i -> NumericLookupTable.LongNumber.getFirstDigit(Math.abs(i.longValue()));

            case Long l -> {
                long val = l;
                if (val == Long.MIN_VALUE) yield 9;
                yield NumericLookupTable.LongNumber.getFirstDigit(Math.abs(val));
            }

            case BigInteger bi -> {
                BigInteger absBi = bi.abs();
                if (absBi.signum() == 0) yield 0;
                int digitCount = getBigIntegerDigitCount(absBi);
                yield absBi.divide(BigInteger.TEN.pow(digitCount - 1)).intValue();
            }

            case BigDecimal bd -> {
                if (bd.signum() == 0) yield 0;
                BigDecimalValidator.validateScaleBelowUpperBound(bd);
                BigDecimalValidator.validatePrecisionBelowUpperBound(bd);
                String str = (strategy == LeadingDigitStrategy.LITERAL)
                        ? bd.abs().toPlainString()
                        : bd.abs().stripTrailingZeros().toEngineeringString();
                yield getDigitFromString(str, strategy);
            }

            case Float f -> {
                FloatNumberValidator.validateFinite(f);
                if (f == 0.0f) yield 0;
                yield parseFloatingPointString(Float.toString(Math.abs(f)), strategy);
            }
            case Double d -> {
                DoubleNumberValidator.validateFinite(d);
                if (d == 0.0) yield 0;
                yield parseFloatingPointString(Double.toString(Math.abs(d)), strategy);
            }

            default -> {
                String fallbackStr = value.toString();
                if (fallbackStr == null || fallbackStr.isBlank())
                    throw new IllegalArgumentException(
                            "Unsupported or empty number type: " + value.getClass().getName() + "."
                    );
                yield getDigitFromString(fallbackStr, strategy);
            }
        };
    }

    public int getFirstDigit(
            final LeadingDigitStrategy strategy
    ) {
        ObjectValidator.validateNotNull(strategy, "Strategy");
        return calculateFirstDigit(strategy);
    }

    public int getFirstDigit() {
        return getFirstDigit(LeadingDigitStrategy.LITERAL);
    }

}
