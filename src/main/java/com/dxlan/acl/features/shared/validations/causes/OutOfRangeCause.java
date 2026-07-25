package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record OutOfRangeCause(
        ValidationTarget target,
        String targetValue,
        String requiredConstraint
) implements ValidationCause {

    public static OutOfRangeCause of(
            final ValidationTarget target,
            final String targetValue,
            final String requiredConstraint
    ) {
        return new OutOfRangeCause(
                target,
                targetValue,
                requiredConstraint
        );
    }

    @Override
    public String toMessage() {
        return "The parameter '" +
                target.displayName() +
                "' (" + targetValue + ")" +
                " is out of the valid range." +
                " Required: " + requiredConstraint + ".";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.OUT_OF_RANGE;
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
