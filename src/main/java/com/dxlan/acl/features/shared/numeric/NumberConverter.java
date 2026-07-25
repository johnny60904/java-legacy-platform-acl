package com.dxlan.acl.features.shared.numeric;

import java.math.BigDecimal;

public final class NumberConverter {

    private NumberConverter() {
        throw new AssertionError();
    }

    public static BigDecimal toSafeBigDecimal(
            final Number value
    ) {
        try {
            return new BigDecimal(value.toString());
        } catch (Exception e) {
            throw new IllegalArgumentException("Unsupported number implementation.");
        }
    }

}
