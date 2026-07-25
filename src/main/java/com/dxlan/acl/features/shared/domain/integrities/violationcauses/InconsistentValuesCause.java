package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record InconsistentValuesCause(
        String violationType,
        ViolationTarget mainTarget,
        ViolationTarget leftTarget,
        String leftValue,
        ViolationTarget rightTarget,
        String rightValue,
        String condition,
        String conditionValue
) implements ViolationCause {

    public static InconsistentValuesCause of(
            final String violationType,
            final ViolationTarget mainTarget,
            final ViolationTarget leftTarget,
            final String leftValue,
            final ViolationTarget rightTarget,
            final String rightValue,
            final String condition,
            final String conditionValue
    ) {
        return new InconsistentValuesCause(
                violationType,
                mainTarget,
                leftTarget,
                leftValue,
                rightTarget,
                rightValue,
                condition,
                conditionValue
        );
    }

    @Override
    public String toMessage() {
        return "The " + violationType +
                " of " + leftTarget.displayName() +
                " (" + leftValue + ")" +
                " and " + rightTarget.displayName() +
                " (" + rightValue + ")" +
                " must be identical when " +
                condition +
                " is [" + conditionValue +"].";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.INCONSISTENT_VALUES;
    }

    @Override
    public ViolationTarget target() {
        return mainTarget;
    }

    @Override
    public String value() {
        return leftValue + ", " + rightValue;
    }

}
