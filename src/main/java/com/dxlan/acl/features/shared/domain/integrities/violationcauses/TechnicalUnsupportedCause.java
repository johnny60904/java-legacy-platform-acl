package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record TechnicalUnsupportedCause(
        ViolationTarget target,
        String targetValue,
        String reason
) implements ViolationCause {

    public static TechnicalUnsupportedCause of(
            final ViolationTarget target,
            final String targetValue,
            final String reason
    ) {
        return new TechnicalUnsupportedCause(
                target,
                targetValue,
                reason
        );
    }

    @Override
    public String toMessage() {
        return target.displayName() +
                " (" + targetValue + ")" +
                " is unsupported due to" +
                reason;
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.TECHNICAL_UNSUPPORTED;
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
