package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record DisallowedValueCause(
        ValidationTarget target,
        String targetValue,
        String allowedOptions
) implements ValidationCause {

    public static DisallowedValueCause of(
            final ValidationTarget target,
            final String targetValue,
            final String allowedOptions
    ) {
        return new DisallowedValueCause(
                target,
                targetValue,
                allowedOptions
        );
    }

    @Override
    public String toMessage() {
        return "The provided value '" +
                targetValue + "'" +
                " for parameter '" +
                target.displayName() + "'" +
                " is disallowed." +
                " Required: " + allowedOptions + ".";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.DISALLOWED_VALUE;
    }

    @Override
    public ValidationTarget target() {
        return target;
    }

    @Override
    public String value() {
        return targetValue;
    }

}
