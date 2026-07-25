package com.dxlan.acl.features.shared.arrays;

import java.util.Objects;

public final class ArrayGuard {

    private ArrayGuard() {
        throw new AssertionError();
    }

    /// Object Type Array
    public static <T> T[] requireNotNull(
            final T[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static <T> T[] requireNotNull(
            final T[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    /// Primitive Type Array
    public static byte[] requireNotNull(
            final byte[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static byte[] requireNotNull(
            final byte[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static short[] requireNotNull(
            final short[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static short[] requireNotNull(
            final short[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static int[] requireNotNull(
            final int[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static int[] requireNotNull(
            final int[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static long[] requireNotNull(
            final long[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static long[] requireNotNull(
            final long[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static float[] requireNotNull(
            final float[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static float[] requireNotNull(
            final float[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static double[] requireNotNull(
            final double[] array,
            final String name
    ) {
        ArrayValidator.validateNotNull(array, name);
        return array;
    }

    public static double[] requireNotNull(
            final double[] array
    ) {
        ArrayValidator.validateNotNull(array);
        return array;
    }

    public static <T> T[] requireLengthBelowSecurityBound(
            final T[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static <T> T[] requireLengthBelowSecurityBound(
            final T[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static byte[] requireLengthBelowSecurityBound(
            final byte[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static byte[] requireLengthBelowSecurityBound(
            final byte[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static short[] requireLengthBelowSecurityBound(
            final short[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static short[] requireLengthBelowSecurityBound(
            final short[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static int[] requireLengthBelowSecurityBound(
            final int[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static int[] requireLengthBelowSecurityBound(
            final int[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static long[] requireLengthBelowSecurityBound(
            final long[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static long[] requireLengthBelowSecurityBound(
            final long[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static float[] requireLengthBelowSecurityBound(
            final float[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static float[] requireLengthBelowSecurityBound(
            final float[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static double[] requireLengthBelowSecurityBound(
            final double[] array,
            final String name
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array, name);
        return array;
    }

    public static double[] requireLengthBelowSecurityBound(
            final double[] array
    ) {
        ArrayValidator.validateLengthBelowSecurityBound(array);
        return array;
    }

    public static <T> T[] requireNotEmpty(
            final T[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static <T> T[] requireNotEmpty(
            final T[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static byte[] requireNotEmpty(
            final byte[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static byte[] requireNotEmpty(
            final byte[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static short[] requireNotEmpty(
            final short[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static short[] requireNotEmpty(
            final short[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static int[] requireNotEmpty(
            final int[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static int[] requireNotEmpty(
            final int[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static long[] requireNotEmpty(
            final long[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static long[] requireNotEmpty(
            final long[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static float[] requireNotEmpty(
            final float[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static float[] requireNotEmpty(
            final float[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static double[] requireNotEmpty(
            final double[] array,
            final String name
    ) {
        ArrayValidator.validateNotEmpty(array, name);
        return array;
    }

    public static double[] requireNotEmpty(
            final double[] array
    ) {
        ArrayValidator.validateNotEmpty(array);
        return array;
    }

    public static <T> T[] requireNoneNull(
            final T[] array,
            final String name
    ) {
        ArrayValidator.validateNoneNull(array, name);
        return array;
    }

    public static <T> T[] requireNoneNull(
            final T[] array
    ) {
        ArrayValidator.validateNoneNull(array);
        return array;
    }

    public static <T> T[] requireHas(
            final T[] array,
            final T candidate,
            final String name,
            final String candidateName
    ) {
        ArrayValidator.validateHas(array, candidate, name, candidateName);
        return array;
    }

    public static <T> T[] requireHas(
            final T[] array,
            final T candidate,
            final String candidateName
    ) {
        ArrayValidator.validateHas(array, candidate, candidateName);
        return array;
    }

    public static <T> T[] requireHas(
            final T[] array,
            final T candidate
    ) {
        ArrayValidator.validateHas(array, candidate);
        return array;
    }

}
