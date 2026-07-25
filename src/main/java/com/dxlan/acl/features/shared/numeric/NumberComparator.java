package com.dxlan.acl.features.shared.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public final class NumberComparator {

    private NumberComparator() {
        throw new AssertionError();
    }

    private static boolean isFloatingPoint(
            final Number number
    ) {
        return number instanceof Double || number instanceof Float;
    }

    private static BigDecimal toBigDecimal(
            final Number number
    ) {
        return switch (number) {
            case BigDecimal bd -> bd;
            case BigInteger bi -> new BigDecimal(bi);
            case Integer i     -> BigDecimal.valueOf(i);
            case Long l        -> BigDecimal.valueOf(l);
            case Double d      -> BigDecimal.valueOf(d);
            case Float f       -> BigDecimal.valueOf(f);
            default            -> new BigDecimal(number.toString());
        };
    }

    private static BigInteger toBigInteger(
            final Number number
    ) {
        return switch (number) {
            case BigInteger bi -> bi;
            case Integer i     -> BigInteger.valueOf(i);
            case Long l        -> BigInteger.valueOf(l);
            default            -> BigInteger.valueOf(number.longValue());
        };
    }

    public static int compare(
            final Number left,
            final Number right
    ) {
        Objects.requireNonNull(left, "First number must not be null.");
        Objects.requireNonNull(right, "Second number must not be null.");

        if (left instanceof Double floatLeft) DoubleNumberValidator.validateNotNaN(floatLeft);
        if (left instanceof Float floatLeft) FloatNumberValidator.validateNotNaN(floatLeft);
        if (right instanceof Double doubleRight) DoubleNumberValidator.validateNotNaN(doubleRight);
        if (right instanceof Float floatRight) FloatNumberValidator.validateNotNaN(floatRight);

        return switch(left) {
            case Integer integerLeft when right instanceof Integer integerRight ->
                    Integer.compare(integerLeft, integerRight);

            case Long longLeft when right instanceof Long longRight -> Long.compare(longLeft, longRight);

                case BigDecimal bigDecimalLeft -> bigDecimalLeft.compareTo(toBigDecimal(right));
                case BigInteger bigIntegerLeft -> {
                    if (right instanceof BigInteger bigIntegerRight)
                        yield bigIntegerLeft.compareTo(bigIntegerRight);
                    if (right instanceof BigDecimal bigDecimalRight)
                        yield new BigDecimal(bigIntegerLeft).compareTo(bigDecimalRight);
                    yield bigIntegerLeft.compareTo(toBigInteger(right));
                }

                default -> {
                    if (right instanceof BigDecimal bigDecimalRight) {
                        yield toBigDecimal(left).compareTo(bigDecimalRight);
                    }
                    if (right instanceof BigInteger bigIntegerRight) {
                        yield toBigInteger(left).compareTo(bigIntegerRight);
                    }
                    if (isFloatingPoint(left) || isFloatingPoint(right)) {
                        yield Double.compare(left.doubleValue(), right.doubleValue());
                    }
                    yield Long.compare(left.longValue(), right.longValue());
                }
        };
    }

}
