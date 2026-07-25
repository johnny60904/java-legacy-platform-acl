package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record MissingRequiredCause(
        ViolationTarget target,
        String targetValue,
        String description
) implements ViolationCause {

    public static MissingRequiredCause ofNull(
            final ViolationTarget target
    ) {
        return new MissingRequiredCause(
                target,
                "Null",
                ""
        );
    }

    public static MissingRequiredCause ofNull(
            final ViolationTarget target,
            final String description
    ) {
        return new MissingRequiredCause(
                target,
                "Null",
                description
        );
    }

    public static MissingRequiredCause of(
            final ViolationTarget target,
            final String targetValue
    ) {
        return new MissingRequiredCause(
                target,
                targetValue,
                ""
        );
    }

    public static MissingRequiredCause of(
            final ViolationTarget target,
            final String targetValue,
            final String description
    ) {
        return new MissingRequiredCause(
                target,
                targetValue,
                description
        );
    }

    @Override
    public String toMessage() {
        return target.displayName() +
                " (" + targetValue + ") must be specified" +
                description + ".";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.MISSING_REQUIRED;
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
