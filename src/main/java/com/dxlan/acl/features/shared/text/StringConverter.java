package com.dxlan.acl.features.shared.text;

import java.util.Locale;
import java.util.Objects;

public final class StringConverter {

    private StringConverter() {
        throw new AssertionError();
    }

    public static <T> String fromAny(
            final T input
    ) {
        return String.valueOf(input);
    }

    public static <T> String fromAny(
            final T input,
            final String nullDefault
    ) {
        return Objects.toString(input, nullDefault);
    }

    public static String toLowerInvariant(
            final String value
    ) {
        TextValidator.validateHasText(value, "Value");
        return value.toLowerCase(Locale.ROOT).trim();
    }

    public static String toUpperInvariant(
            final String value
    ) {
        TextValidator.validateHasText(value, "Value");
        return value.toUpperCase(Locale.ROOT).trim();
    }

    public static String toInvariantLowerFirst(
            final String value
    ) {
        TextValidator.validateHasText(value, "Value");
        String normalized = StringNormalizer.toInvariant(value);
        char firstChar = normalized.charAt(0);
        if (Character.isLowerCase(firstChar)) return normalized;
        return Character.toLowerCase(firstChar) +
                normalized.substring(1);
    }

    public static String toInvariantUpperFirst(
            final String value
    ) {
        TextValidator.validateHasText(value, "Value");
        String normalized = StringNormalizer.toInvariant(value);
        char firstChar = normalized.charAt(0);
        if (Character.isUpperCase(firstChar)) return normalized;
        return Character.toUpperCase(firstChar) +
                normalized.substring(1);
    }

}
