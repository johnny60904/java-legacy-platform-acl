package com.dxlan.acl.features.shared.numeric;

public final class NumericLookupTable {

    private NumericLookupTable() {
        throw new AssertionError();
    }

    public static final class LongNumber {
        private LongNumber() { throw new AssertionError(); }

        public static final long[] DIGIT_LIMITS_TABLE = {
                9L, 99L, 999L, 9_999L, 99_999L, 999_999L, 9_999_999L, 99_999_999L, 999_999_999L,
                9_999_999_999L, 99_999_999_999L, 999_999_999_999L, 9_999_999_999_999L, 99_999_999_999_999L,
                999_999_999_999_999L, 9_999_999_999_999_999L, 99_999_999_999_999_999L, 999_999_999_999_999_999L,
                Long.MAX_VALUE
        };

        public static final long[] TEN_POWERS_TABLE = {
                1L, 10L, 100L, 1_000L, 10_000L, 100_000L, 1_000_000L, 10_000_000L, 100_000_000L,
                1_000_000_000L, 10_000_000_000L, 100_000_000_000L, 1_000_000_000_000L, 10_000_000_000_000L,
                100_000_000_000_000L, 1_000_000_000_000_000L, 10_000_000_000_000_000L, 100_000_000_000_000_000L,
                1_000_000_000_000_000_000L
        };

        public static int getDigitCount(
                final long absValue
        ) {
            if (absValue == 0) return 1;
            for (int i = 0; ; i++) {
                if (absValue <= DIGIT_LIMITS_TABLE[i]) return i + 1;
            }
        }

        public static int getFirstDigit(
                final long absValue
        ) {
            if (absValue == 0) return 0;
            int digitCount = 1;
            for (int i = 0; ; i++) {
                if (absValue <= DIGIT_LIMITS_TABLE[i]) {
                    digitCount = i + 1;
                    break;
                }
            }
            /// N / 10^(N-1)
            /// e.g. 523 / TEN_POWERS_TABLE[3 - 1] -> 523 / 100 = 5
            return (int) (absValue / TEN_POWERS_TABLE[digitCount - 1]);
        }
    }

}
