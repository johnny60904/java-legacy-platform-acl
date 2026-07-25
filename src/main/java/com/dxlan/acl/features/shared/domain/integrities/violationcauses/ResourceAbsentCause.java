package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record ResourceAbsentCause(
        ViolationTarget target,
        String targetValue,
        String resourceSourceName
) implements ViolationCause {

    public static ResourceAbsentCause of(
            final ViolationTarget target,
            final String targetValue,
            final String resourceSourceName
    ) {
        return new ResourceAbsentCause(
                target,
                targetValue,
                resourceSourceName
        );
    }

    @Override
    public String toMessage() {
        return "Required resource [" +
                target.displayName() + "]" +
                " (" + targetValue + ")" +
                " is absent from '" +
                resourceSourceName + "'.";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.RESOURCE_ABSENT;
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
