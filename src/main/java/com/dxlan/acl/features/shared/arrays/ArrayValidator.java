package com.dxlan.acl.features.shared.arrays;

import java.util.Arrays;
import java.util.Objects;

public final class ArrayValidator {

    private static final String DEFAULT_NAME = "Array";

    private ArrayValidator() {
        throw new AssertionError();
    }

    private static void throwWhenEmpty(
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must not be empty."
        );
    }

    private static void throwWhenContainNull(
            final String name
    ) {
        throw new IllegalArgumentException(
                name + " must not contain any null items / elements."
        );
    }

    private static void throwWhenLengthTooLarge(
            final String name,
            final int length
    ) {
        throw new IllegalArgumentException(
                name + " length (" + length + ") is too large, rejected to prevent OOM."
        );
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
    }

    public static void validateNotNull(
            final Object array,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(array, name + " must not be null.");
    }

    public static void validateNotNull(
            final Object array
    ) {
        validateNotNull(array, DEFAULT_NAME);
    }

    public static <T> void validateNotEmpty(
            final T[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static <T> void validateNotEmpty(
            final T[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final byte[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final byte[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final short[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final short[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final int[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final int[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final long[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final long[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final float[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final float[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final double[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length == 0) throwWhenEmpty(name);
    }

    public static void validateNotEmpty(
            final double[] array
    ) {
        validateNotEmpty(array, DEFAULT_NAME);
    }

    public static <T> void validateLengthBelowSecurityBound(
            final T[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static <T> void validateLengthBelowSecurityBound(
            final T[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final byte[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final byte[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final short[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final short[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final int[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final int[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final long[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final long[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final float[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final float[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static void validateLengthBelowSecurityBound(
            final double[] array,
            final String name
    ) {
        validateNotNull(array, name);
        if (array.length <= ArrayLengthSecurity.MAXIMUM_BOUND) return;
        throwWhenLengthTooLarge(name, array.length);
    }

    public static void validateLengthBelowSecurityBound(
            final double[] array
    ) {
        validateLengthBelowSecurityBound(array, DEFAULT_NAME);
    }

    public static <T> void validateNoneNull(
            final T[] array,
            final String name
    ) {
        validateNotEmpty(array, name);
        validateLengthBelowSecurityBound(array, name);
        for (T element : array) {
            if (element == null) throwWhenContainNull(name);
        }
    }

    public static <T> void validateNoneNull(
            final T[] array
    ) {
        validateNoneNull(array, DEFAULT_NAME);
    }

    public static <T> void validateHas(
            final T[] array,
            final T candidate,
            final String name,
            final String candidateName
    ) {
        validateNoneNull(array, name);
        Objects.requireNonNull(candidate, "Candidate must not be null.");
        Objects.requireNonNull(candidateName, "Candidate name must not be null.");
        if (array.length <= ArrayLengthSecurity.THRESHOLD) {
            for (T element : array) if(element.equals(candidate)) return;
            throw new IllegalArgumentException(
                    name + " must contain " + candidateName + "."
            );
        }
        Arrays.sort(array);
        if (Arrays.binarySearch(array, candidate) >= 0) return;
        throw new IllegalArgumentException(
                name + " must contain " + candidateName + "."
        );
    }

    public static <T> void validateHas(
            final T[] array,
            final T candidate,
            final String candidateName
    ) {
        validateHas(array, candidate, DEFAULT_NAME, candidateName);
    }

    public static <T> void validateHas(
            final T[] array,
            final T candidate
    ) {
        validateHas(array, candidate, DEFAULT_NAME, "required candidate");
    }

}
