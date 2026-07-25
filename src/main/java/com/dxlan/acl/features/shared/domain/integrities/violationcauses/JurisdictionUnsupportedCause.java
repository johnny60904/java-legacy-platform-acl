package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record JurisdictionUnsupportedCause(
        ViolationTarget target,
        String targetValue,
        String subject
) implements ViolationCause {

    public static JurisdictionUnsupportedCause of(
            final ViolationTarget target,
            final String targetValue,
            final String subject
    ) {
        return new JurisdictionUnsupportedCause(
                target,
                targetValue,
                subject
        );
    }

    @Override
    public String toMessage() {
        return target.displayName() +
                " (" + targetValue + ")" +
                " is unsupported for " +
                subject +
                " in the current jurisdiction.";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.JURISDICTION_UNSUPPORTED;
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
