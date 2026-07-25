package com.dxlan.acl.features.shared.arrays;

import java.util.Objects;

public final class ArrayJoiner {

    private static final String DEFAULT_NAME = "Array";

    private ArrayJoiner() {
        throw new AssertionError();
    }

    private static void validateDelimiterNotNull(
            final String delimiter
    ) {
        Objects.requireNonNull(delimiter, "Delimiter must not be null.");
    }

    public static String join(
            final byte[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final byte[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

    public static String join(
            final short[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final short[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

    public static String join(
            final int[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final int[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

    public static String join(
            final long[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final long[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

    public static String join(
            final float[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final float[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

    public static String join(
            final double[] array,
            final String delimiter
    ) {
        ArrayValidator.validateNotNull(array, DEFAULT_NAME);
        validateDelimiterNotNull(delimiter);
        if (array.length == 0) return "";
        ArrayValidator.validateLengthBelowSecurityBound(array, DEFAULT_NAME);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(
                    String.valueOf(array[i])
            );
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String join(
            final double[] array
    ) {
        return ArrayJoiner.join(array, ", ");
    }

}
