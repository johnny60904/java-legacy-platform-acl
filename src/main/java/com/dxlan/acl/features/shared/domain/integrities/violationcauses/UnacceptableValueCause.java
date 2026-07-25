package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record UnacceptableValueCause(
        ViolationTarget target,
        String targetValue,
        String acceptedOptions
) implements ViolationCause {

    public static UnacceptableValueCause of(
            final ViolationTarget target,
            final String targetValue,
            final String acceptedOptions
    ) {
        return new UnacceptableValueCause(
                target,
                targetValue,
                acceptedOptions
        );
    }

    @Override
    public String toMessage() {
        return "Value '" + targetValue +
                "' is unacceptable for " +
                target.displayName() +
                ". Acceptable value(s): " +
                acceptedOptions;
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.UNACCEPTABLE_VALUE;
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
