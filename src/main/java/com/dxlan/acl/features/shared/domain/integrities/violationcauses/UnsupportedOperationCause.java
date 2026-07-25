package com.dxlan.acl.features.shared.domain.integrities.violationcauses;

import com.dxlan.acl.features.shared.common.Operation;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCategory;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;

public record UnsupportedOperationCause(
        ViolationTarget target,
        String targetValue,
        Operation operation,
        String description
)  implements ViolationCause {

    public static UnsupportedOperationCause of(
            final ViolationTarget target,
            final String targetValue,
            final Operation operation,
            final String description
    ) {
        return new UnsupportedOperationCause(
                target,
                targetValue,
                operation,
                description
        );
    }

    @Override
    public String toMessage() {
        return "Operation '" +
                operation.displayName() +
                "' cannot be performed with " +
                target.displayName() +
                " [" + targetValue + "]" +
                ". Required: " +
                description + ".";
    }

    @Override
    public ViolationCategory category() {
        return ViolationCategory.UNSUPPORTED_OPERATION;
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
