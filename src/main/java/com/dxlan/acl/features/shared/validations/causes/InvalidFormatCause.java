package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record InvalidFormatCause(
        ValidationTarget target,
        String targetValue,
        String requiredFormat
) implements ValidationCause {

    public static InvalidFormatCause of(
            final ValidationTarget target,
            final String targetValue,
            final String requiredFormat
    ) {
        return new InvalidFormatCause(
                target,
                targetValue,
                requiredFormat
        );
    }

    @Override
    public String toMessage() {
        return "The value provided " +
                "(" + targetValue + ")" +
                " for parameter '" +
                target.displayName() + "'" +
                " does not match the required format" +
                " [" + requiredFormat + "].";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.INVALID_FORMAT;
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
