package com.dxlan.acl.features.shared.text;

import com.dxlan.acl.features.shared.collections.CollectionValidator;

import java.util.Objects;
import java.util.Set;

public final class TextValidator {

    private static final String DEFAULT_NAME = "Text";

    private TextValidator() {
        throw new AssertionError();
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) throw new IllegalArgumentException("Name must not be blank.");
    }

    public static void validateNotNull(
            final String text,
            final String name
    ) {
        validateNameHasText(name);
        if (text == null) {
            throw new IllegalArgumentException(
                    name + " must not be null."
            );
        }
    }

    public static void validateNotNull(
            final String text
    ) {
        validateNotNull(text, DEFAULT_NAME);
    }

    public static void validateNotEmpty(
            final String text,
            final String name
    ) {
        validateNameHasText(name);
        if (text.isEmpty()) {
            throw new IllegalArgumentException(
                    name + " must not be empty."
            );
        }
    }

    public static void validateNotEmpty(
            final String text
    ) {
        validateNotEmpty(text, DEFAULT_NAME);
    }

    public static void validateNotBlank(
            final String text,
            final String name
    ) {
        validateNameHasText(name);
        if (text.isBlank()) {
            throw new IllegalArgumentException(
                    name + " must not be blank."
            );
        }
    }

    public static void validateNotBlank(
            final String text
    ) {
        validateNotBlank(text, DEFAULT_NAME);
    }

    public static void validateHasText(
            final String text,
            final String name
    ) {
        validateNameHasText(name);
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(
                    name + " must not be null or blank."
            );
        }
    }

    public static void validateHasText(
            final String text
    ) {
        validateHasText(text, DEFAULT_NAME);
    }

    public static void validateAnyOf(
            final String value,
            final Set<String> allowedValues,
            final String name
    ) {
        validateHasText(value, name);
        CollectionValidator.validateNoneNull(allowedValues, "AllowedValues");
        if (!allowedValues.contains(value)) {
            throw new IllegalArgumentException(
                    name + " must be one of the allowed values: " +
                    "[" + String.join(", ", allowedValues) + "]" +
                    ", but was: " + value + "."
            );
        }
    }

    public static void validateAnyOf(
            final String value,
            final Set<String> allowedValues
    ) {
        validateAnyOf(value, allowedValues, DEFAULT_NAME);
    }

    public static void validateAnyOf(
            final String value,
            final String name,
            final String... allowedValues
    ) {
        validateAnyOf(value, Set.of(allowedValues), name);
    }

    public static void validateAnyOf(
            final String value,
            final String... allowedValues
    ) {
        validateAnyOf(value, Set.of(allowedValues), DEFAULT_NAME);
    }

}
