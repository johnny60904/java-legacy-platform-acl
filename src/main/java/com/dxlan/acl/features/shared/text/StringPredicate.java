package com.dxlan.acl.features.shared.text;

public final class StringPredicate {

    private StringPredicate() {
        throw new AssertionError();
    }

    public static boolean isNullOrWhiteSpace(final String value) {
        return (
            (value == null) ||
            (value.isBlank())
        );
    }

    public static boolean isNotBlank(final String value) {
        return (
            (value != null) &&
            (!value.isBlank())
        );
    }

}
