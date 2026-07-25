package com.dxlan.acl.features.shared.validations.taxonomy;

public record QueryValidation(
        ValidationCause cause
) {

    public static QueryValidation of(
            final ValidationCause cause
    ) {
        return new QueryValidation(cause);
    }

    public String toMessage() {
        return cause.toMessage();
    }

}
