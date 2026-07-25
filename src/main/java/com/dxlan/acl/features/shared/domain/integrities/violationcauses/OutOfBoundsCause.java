package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record OutOfBoundsCause(
        ViolationTarget target,
        String targetValue,
        String subject,
        String requiredConstraint
) implements ViolationCause {

    public static OutOfBoundsCause of(
            final ViolationTarget target,
            final String targetValue,
            final String subject,
            final String requiredConstraint
    ) {
        return new OutOfBoundsCause(
                target,
                targetValue,
                subject,
                requiredConstraint
        );
    }

    @Override
    public String toMessage() {
        return target.displayName() +
                " (" + targetValue + ")" +
                " is out of bounds for " +
                subject +
                ". Required: " +
                requiredConstraint;
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.OUT_OF_BOUNDS;
    }

    @Override
    public ViolationTarget target() {
        return target;
    }

    @Override
    public String value() {
        return targetValue;
    }

}
