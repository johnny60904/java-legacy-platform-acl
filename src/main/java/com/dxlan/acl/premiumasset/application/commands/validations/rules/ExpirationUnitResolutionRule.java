package com.dxlan.acl.premiumasset.application.commands.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.text.StringConverter;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

public class ExpirationUnitResolutionRule {

    private ExpirationUnitResolutionRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<String> create(
                final String expirationUnitToken
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.CONTAINS,
                    expirationUnitToken,
                    expirationUnit_Token -> ExpirationUnit.INVARIANT_PARSABLE_TOKENS.contains(
                            StringConverter.toUpperInvariant(expirationUnit_Token)
                    )
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be specified by one of the following valid value: " +
                    String.join(", ", ExpirationUnit.INVARIANT_PARSABLE_TOKENS);
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
