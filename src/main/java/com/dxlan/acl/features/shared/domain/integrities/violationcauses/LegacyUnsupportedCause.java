package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record LegacyUnsupportedCause(
        ViolationTarget target,
        String targetValue
) implements ViolationCause {

    public static LegacyUnsupportedCause of(
            final ViolationTarget target,
            final String targetValue
    ) {
        return new LegacyUnsupportedCause(
                target,
                targetValue
        );
    }

    @Override
    public String toMessage() {
        return target.displayName() +
                " (" + targetValue + ")" +
                " is unsupported and has been decommissioned.";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.LEGACY_UNSUPPORTED;
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
