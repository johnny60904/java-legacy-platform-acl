package com.dxlan.acl.features.shared.arrays;

import java.util.Arrays;

public final class ArrayPredicate {

    private ArrayPredicate() {
        throw new AssertionError();
    }

    public static <T> boolean contains(
            final T[] array,
            final T candidate,
            final boolean tolerateNullElement
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        if (candidate == null) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (!tolerateNullElement) ArrayValidator.validateNoneNull(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (T element : array) {
                if (element.equals(candidate)) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static <T> boolean contains(
            final T[] array,
            final T candidate
    ) {
        return contains(array, candidate, false);
    }

    public static boolean contains(
            final byte[] array,
            final byte candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (byte element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static boolean contains(
            final short[] array,
            final short candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (short element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static boolean contains(
            final int[] array,
            final int candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (int element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static boolean contains(
            final long[] array,
            final long candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (long element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static boolean contains(
            final float[] array,
            final float candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (float element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

    public static boolean contains(
            final double[] array,
            final double candidate
    ) {
        ArrayValidator.validateNotNull(array);
        if (array.length == 0) return false;
        ArrayValidator.validateLengthBelowSecurityBound(array);
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (double element : array) {
                if (element == candidate) return true;
            }
            return false;
        }
        Arrays.sort(array);
        int index = Arrays.binarySearch(array, candidate);
        return index >= 0;
    }

}
