package com.dxlan.acl.premiumasset.application.common.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

import java.util.Locale;

public final class SessionProcessIdRangeRule {

    private SessionProcessIdRangeRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Integer> create(
                final int sessionProcessId
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.WITHIN,
                    sessionProcessId,
                    sessionProcess_Id ->
                            sessionProcess_Id >= ClusterPhysicsMetadata.UserSession.ID_LOWER_BOUND &&
                            sessionProcess_Id <= ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be within closed range: [" +
                    ClusterPhysicsMetadata.UserSession.ID_LOWER_BOUND + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND) +
                    "]";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
