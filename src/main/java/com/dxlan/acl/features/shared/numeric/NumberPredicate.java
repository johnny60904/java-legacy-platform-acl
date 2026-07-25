package com.dxlan.acl.features.shared.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberPredicate {

    private NumberPredicate() {
        throw new AssertionError();
    }

    public static int signum(
            final Number value
    ) {
        return switch(value) {
            case Integer i     -> Integer.compare(i, 0);
            case Long l        -> Long.compare(l, 0L);
            case BigDecimal bd -> bd.signum();
            case BigInteger bi -> bi.signum();
            case Double d      -> {
                DoubleNumberValidator.validateNotNaN(d);
                yield Double.compare(d, 0.0);
            }
            case Float f       -> {
                FloatNumberValidator.validateNotNaN(f);
                yield Float.compare(f, 0.0f);
            }
            case Short s       -> Short.compare(s, (short) 0);
            case Byte b        -> Byte.compare(b, (byte) 0);
            default            -> {
                double dd = value.doubleValue();
                DoubleNumberValidator.validateNotNaN(dd);
                yield Double.compare(dd, 0.0);
            }
        };
    }

    public static boolean isPositive(
            final Number value
    ) {
        return value != null && signum(value) > 0;
    }

    public static boolean isPositiveOrZero(
            final Number value
    ) {
        return value != null && signum(value) >= 0;
    }

    public static boolean isNegative(
            final Number value
    ) {
        return value != null && signum(value) < 0;
    }

    public static boolean isNegativeOrZero(
            final Number value
    ) {
        return value != null && signum(value) <= 0;
    }

}
