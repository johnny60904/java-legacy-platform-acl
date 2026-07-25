package com.dxlan.acl.features.shared.text;

/// Convention over Configuration
public final class CamelCaseConverter {

    private CamelCaseConverter() {
        throw new AssertionError();
    }

    /**
     * Converts a CamelCase or PascalCase string into SCREAMING_SNAKE_CASE.
     *
     * <p><b>Preconditions:</b>
     * <ul>
     *   <li>The input must be a well-formed ASCII alphanumeric string.
     *   <li>The input must strictly adhere to CamelCase or PascalCase conventions.
     * </ul>
     *
     * This method is optimized for the happy path using Design by Contract (DbC).
     * It bypasses input validation to achieve zero-allocation, maximum performance.
     *
     * @param input a well-formed ASCII Camel/Pascal case string
     * @return the uppercase screaming snake case representation
     */
    public static String toScreamingSnakeCase(
            final String input
    ) {
        TextValidator.validateHasText(input, "Input");

        final int length = input.length();

        final char[] src = new char[length];
        input.getChars(0, length, src, 0);

        final char[] dst = new char[length << 1];
        int dstIdx = 0;

        boolean prevIsLower = false;
        boolean prevIsDigit = false;

        for (int i = 0; i < length; i++) {
            final char c = src[i];

            final boolean isUpper = (c >= 'A' && c <= 'Z');
            final boolean isDigit = (c >= '0' && c <= '9');
            final boolean isLower = (c >= 'a' && c <= 'z');

            if (i > 0) {
                if ((isUpper && prevIsLower) || (isDigit != prevIsDigit)) {
                    dst[dstIdx++] = '_';
                } else if (isUpper && !prevIsLower && i < length - 1) {
                    final char nextC = src[i + 1];
                    if (nextC >= 'a' && nextC <= 'z') {
                        dst[dstIdx++] = '_';
                    }
                }
            }

            /// to upper case
            dst[dstIdx++] = isLower ? (char) (c - 32) : c;

            prevIsLower = isLower;
            prevIsDigit = isDigit;
        }

        return new String(dst, 0, dstIdx);
    }

}
