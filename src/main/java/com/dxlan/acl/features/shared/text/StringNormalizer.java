package com.dxlan.acl.features.shared.text;

import com.dxlan.acl.features.shared.arrays.ArrayValidator;

import java.util.Locale;

public final class StringNormalizer {

    private StringNormalizer() {
        throw new AssertionError();
    }

    public static String toInvariant(
            final String value
    ) {
        TextValidator.validateHasText(value, "Value");

        String upperInvariant = value.toUpperCase(Locale.ROOT);
        String lowerInvariant = value.toLowerCase(Locale.ROOT);

        StringBuilder result = new StringBuilder(value.length());

        int[] originalCodePoints = value.codePoints().toArray();
        int[] upperCodePoints = upperInvariant.codePoints().toArray();
        int[] lowerCodePoints = lowerInvariant.codePoints().toArray();

        ArrayValidator.validateLengthBelowSecurityBound(originalCodePoints);
        ArrayValidator.validateLengthBelowSecurityBound(upperCodePoints);
        ArrayValidator.validateLengthBelowSecurityBound(lowerCodePoints);

        int length = Math.min(
                originalCodePoints.length,
                Math.min(
                        upperCodePoints.length,
                        lowerCodePoints.length
                )
        );

        for (int i = 0; i < length; i++) {
            int orig = originalCodePoints[i];
            if (Character.isUpperCase(orig)) result.appendCodePoint(upperCodePoints[i]);
            else if (Character.isLowerCase(orig)) result.appendCodePoint(lowerCodePoints[i]);
            else result.appendCodePoint(orig);
        }

        return result.toString();
    }

}
