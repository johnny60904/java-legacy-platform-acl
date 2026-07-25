package com.dxlan.acl.features.shared.text;

import java.util.Set;

public final class TextGuard {

    private TextGuard() {
        throw new AssertionError();
    }

    public static String requireNotNull(
            final String value,
            final String name
    ) {
        TextValidator.validateNotNull(value, name);
        return value;
    }

    public static String requireNotNull(
            final String value
    ) {
        TextValidator.validateNotNull(value);
        return value;
    }

    public static String requireNotEmpty(
            final String value,
            final String name
    ) {
        TextValidator.validateNotEmpty(value, name);
        return value;
    }

    public static String requireNotEmpty(
            final String value
    ) {
        TextValidator.validateNotEmpty(value);
        return value;
    }

    public static String requireNotBlank(
            final String value,
            final String name
    ) {
        TextValidator.validateNotBlank(value, name);
        return value;
    }

    public static String requireNotBlank(
            final String value
    ) {
        TextValidator.validateNotBlank(value);
        return value;
    }

    public static String requireHasText(
            final String value,
            final String name
    ) {
        TextValidator.validateHasText(value, name);
        return value;
    }

    public static String requireHasText(
            final String value
    ) {
        TextValidator.validateHasText(value);
        return value;
    }

    public static String requireAnyOf(
            final String value,
            final Set<String> allowedValues,
            final String name
    ) {
        TextValidator.validateAnyOf(value, allowedValues, name);
        return value;
    }

    public static String requireAnyOf(
            final String value,
            final Set<String> allowedValues
    ) {
        TextValidator.validateAnyOf(value, allowedValues);
        return value;
    }

    public static String requireAnyOf(
            final String value,
            final String name,
            final String... allowedValues
    ) {
        TextValidator.validateAnyOf(value, name, allowedValues);
        return value;
    }

    public static String requireAnyOf(
            final String value,
            final String... allowedValues
    ) {
        TextValidator.validateAnyOf(value, allowedValues);
        return value;
    }

}
