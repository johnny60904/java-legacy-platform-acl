package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record MissingFieldCause(
        ValidationTarget target,
        String targetValue
) implements ValidationCause {

    public static MissingFieldCause ofNull(
            final ValidationTarget target
    ) {
        return new MissingFieldCause(
                target,
                "Null"
        );
    }

    public static MissingFieldCause ofBlank(
            final ValidationTarget target
    ) {
        return new MissingFieldCause(
                target,
                ""
        );
    }

    public static MissingFieldCause of(
            final ValidationTarget target,
            final String targetValue
    ) {
        return new MissingFieldCause(
                target,
                targetValue
        );
    }

    @Override
    public String toMessage() {
        return "The parameter '" +
                target.displayName() +
                "' (" + targetValue + ")" +
                " is required and must not be null or blank.";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.MISSING_FIELD;
    }

    @Override
    public ValidationTarget target() {
        return target;
    }

    @Override
    public String value() { return targetValue; }

}
