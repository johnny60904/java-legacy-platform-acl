package com.dxlan.acl.features.shared.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public final class NumericDigitCountMeasurer {

    private final Number value;

    public static final DigitStrategy DEFAULT_STRATEGY = DigitStrategy.INTEGER;

    private NumericDigitCountMeasurer(
            final Number value
    ) {
        this.value = Objects.requireNonNull(value, "Value must not be null.");
    }

    private static int getBigIntegerDigitCount(
            final BigInteger bigInteger
    ) {
        if (bigInteger.signum() == 0) return 1;
        final double factor = 0.3_010_299_956_639_812; /// log10(2)
        int digits = (int) (bigInteger.bitLength() * factor) + 1;
        if (bigInteger.compareTo(BigInteger.TEN.pow(digits - 1)) < 0) digits--;
        return digits;
    }

    private static int calculateNegativeScaleIntegerDigits(
            final BigDecimal bigDecimal
    ) {
        /// 1.23e3 (1230) -> precision = 3, scale = -1 -> 3 - (-1) = 4 digits
        return bigDecimal.precision() - bigDecimal.scale();
    }

    private static int parseFloatingPointString(
            final String str,
            final boolean getIntegerPart
    ) {
        if (str.indexOf('E') != -1 || str.indexOf('e') != -1) {
            BigDecimal bigDecimal = new BigDecimal(str).stripTrailingZeros();
            if (getIntegerPart) {
                return (bigDecimal.scale() < 0)
                        ? bigDecimal.precision() - bigDecimal.scale()
                        : bigDecimal.toBigInteger().abs().toString().length();
            } else {
                return Math.max(bigDecimal.scale(), 0);
            }
        }

        int dotIndex = str.indexOf('.');
        if (dotIndex == -1) {
            if (getIntegerPart) {
                int length = str.length();
                return str.charAt(0) == '-' ? length - 1 : length;
            }
            return 0;
        }

        if (getIntegerPart) {
            int intLength = dotIndex;
            if (str.charAt(0) == '-') intLength--;
            return intLength;
        } else {
            int fractionLength = str.length() - dotIndex - 1;
            if (fractionLength == 1 && str.charAt(dotIndex + 1) == '0') return 0;
            return fractionLength;
        }
    }

    private int calculateIntegerDigits() {
        return switch (value) {
            case Integer i -> NumericLookupTable.LongNumber.getDigitCount(
                    Math.abs(i.longValue())
            );
            case Long l -> {
                if (l == Long.MIN_VALUE) yield 19;
                yield NumericLookupTable.LongNumber.getDigitCount(Math.abs(l));
            }
            case Short s -> NumericLookupTable.LongNumber.getDigitCount(
                    Math.abs(s.longValue())
            );
            case Byte b -> NumericLookupTable.LongNumber.getDigitCount(
                    Math.abs(b.longValue())
            );

            case BigInteger bi -> getBigIntegerDigitCount(bi.abs());

            case BigDecimal bd -> {
                BigDecimalValidator.validateScaleBelowUpperBound(bd);
                BigDecimalValidator.validatePrecisionBelowUpperBound(bd);
                if (bd.scale() < 0) yield calculateNegativeScaleIntegerDigits(bd);
                yield getBigIntegerDigitCount(
                        bd.stripTrailingZeros().toBigInteger().abs()
                );
            }

            case Double d -> {
                DoubleNumberValidator.validateFinite(d);
                yield parseFloatingPointString(Double.toString(Math.abs(d)), true);
            }
            case Float f -> {
                FloatNumberValidator.validateFinite(f);
                yield parseFloatingPointString(Float.toString(Math.abs(f)), true);
            }

            default -> {
                try {
                    BigDecimal bd = NumberConverter.toSafeBigDecimal(value);
                    BigDecimalValidator.validateScaleBelowUpperBound(bd);
                    BigDecimalValidator.validatePrecisionBelowUpperBound(bd);
                    if (bd.scale() < 0) yield calculateNegativeScaleIntegerDigits(bd);
                    yield getBigIntegerDigitCount(
                            bd.stripTrailingZeros().toBigInteger().abs()
                    );
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Unsupported number implementation.");
                }
            }
        };
    }

    private int calculateScale() {
        return switch (value) {
            case Integer i  -> 0;
            case Long l -> 0;
            case Short s -> 0;
            case Byte b -> 0;
            case BigInteger bi -> 0;

            case BigDecimal bd -> {
                BigDecimalValidator.validateScaleBelowUpperBound(bd);
                BigDecimalValidator.validatePrecisionBelowUpperBound(bd);
                if (bd.scale() < 0) yield 0;
                yield bd.stripTrailingZeros().scale();
            }
            case Double d -> {
                DoubleNumberValidator.validateFinite(d);
                yield parseFloatingPointString(Double.toString(d), false);
            }
            case Float f -> {
                FloatNumberValidator.validateFinite(f);
                yield parseFloatingPointString(Float.toString(f), false);
            }
            default -> {
                BigDecimal bd = NumberConverter.toSafeBigDecimal(value);
                BigDecimalValidator.validateScaleBelowUpperBound(bd);
                BigDecimalValidator.validatePrecisionBelowUpperBound(bd);
                yield (bd.scale() < 0) ? 0 : bd.stripTrailingZeros().scale();
            }
        };
    }

    public int count(
            final DigitStrategy strategy
    ) {
        Objects.requireNonNull(strategy, "Strategy must not be null.");
        return switch(strategy) {
            case INTEGER -> calculateIntegerDigits();
            case SCALE   -> calculateScale();
            case TOTAL   -> calculateIntegerDigits() + calculateScale();
        };
    }

    public int count() {
        return calculateIntegerDigits();
    }

    public static NumericDigitCountMeasurer of(
            final Number value
    ) {
        return new NumericDigitCountMeasurer(value);
    }

}
