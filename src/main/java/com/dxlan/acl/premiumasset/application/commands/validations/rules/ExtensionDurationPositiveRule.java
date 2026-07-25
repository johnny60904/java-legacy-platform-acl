package com.dxlan.acl.premiumasset.application.commands.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public final class ExtensionDurationPositiveRule {

    private ExtensionDurationPositiveRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Long> create(
                final long extensionDuration
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.GREATER_THAN,
                    extensionDuration,
                    extension_Duration -> extension_Duration > 0
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be > 0";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
