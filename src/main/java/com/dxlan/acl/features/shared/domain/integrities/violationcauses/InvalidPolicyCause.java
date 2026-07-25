package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record InvalidPolicyCause(
        ViolationTarget target,
        String targetValue,
        String subject,
        String subjectValue
) implements ViolationCause {

    public static InvalidPolicyCause of(
            final ViolationTarget target,
            final String targetValue,
            final String subject,
            final String subjectValue
    ) {
        return new InvalidPolicyCause(
                target,
                targetValue,
                subject,
                subjectValue
        );
    }

    @Override
    public String toMessage() {
        return "Incompatible " +
                target.displayName() +
                " [" + targetValue + "]" +
                " for " +
                subject +
                " [" + subjectValue + "]" +
                ".";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.INVALID_POLICY;
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
