package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

import java.util.Locale;

public final class SessionProcessIdValidityConstraint {

    private SessionProcessIdValidityConstraint() { throw new AssertionError(); }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int sessionProcessId
        ) {
            ValidationConstraint<Integer> isPositive = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_THAN,
                    sessionProcessId,
                    sessionProcess_Id -> sessionProcess_Id > 0
            );

            ValidationConstraint<Integer> isWithinTenIntegerDigits = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    sessionProcessId,
                    sessionProcess_Id -> NumericDigitCountMatcher.of(sessionProcess_Id).isDigitCountWithin(
                            ClusterPhysicsMetadata.UserSession.ID_MIN_DIGIT,
                            ClusterPhysicsMetadata.UserSession.ID_MAX_DIGIT
                    )
            );

            ValidationConstraint<Integer> isAtMostIntegerMaxValue = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.LESS_OR_EQUAL,
                    sessionProcessId,
                    sessionProcess_Id ->
                            sessionProcess_Id <= ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND
            );

            return isPositive
                    .and(isWithinTenIntegerDigits)
                    .and(isAtMostIntegerMaxValue);
        }

        public String requirementDescription() {
            return name +
                    " must be > 0" +
                    " and must be an " +
                    ClusterPhysicsMetadata.UserSession.ID_MIN_DIGIT + " to " +
                    ClusterPhysicsMetadata.UserSession.ID_MAX_DIGIT +
                    " digit number" +
                    " and must be <= " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND
                    );
        }

    }

    public static Definition of(
            final String name
    ) {
        return new Definition(
                name
        );
    }

    public static Definition ofDefault() {
        return new Definition(
                ClusterPhysicsMetadata.UserSession.ID_DEFAULT_NAME
        );
    }

}
