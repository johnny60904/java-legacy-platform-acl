package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record DataCorruptedCause(
        ViolationTarget target,
        String corruptedValue,
        String integrityConstraint,
        String violationContextDescription
) implements ViolationCause {

    public static DataCorruptedCause of(
            final ViolationTarget target,
            final String corruptedValue,
            final String integrityConstraint,
            final String violationContextDescription
    ) {
        return new DataCorruptedCause(
                target,
                corruptedValue,
                integrityConstraint,
                violationContextDescription
        );
    }

    @Override
    public String toMessage() {
        return "Critical Data Corruption detected: " +
                " The value (" + corruptedValue + ") of '" +
                target.displayName() + "'" +
                " violated the constraint [" +
                integrityConstraint + "]." +
                " Context: " + violationContextDescription + ".";
    }

    @Override public ViolationCategory category() {
        return ViolationCategory.DATA_CORRUPTED;
    }

    @Override public ViolationTarget target() {
        return target;
    }

    @Override public String value() {
        return corruptedValue;
    }

}
