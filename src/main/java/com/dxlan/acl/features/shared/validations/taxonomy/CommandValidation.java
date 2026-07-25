package com.dxlan.acl.features.shared.validations.taxonomy;

public record CommandValidation(
        ValidationCause cause
) {

    public static CommandValidation of(
            final ValidationCause cause
    ) {
        return new CommandValidation(cause);
    }

    public String toMessage() {
        return cause.toMessage();
    }

}
