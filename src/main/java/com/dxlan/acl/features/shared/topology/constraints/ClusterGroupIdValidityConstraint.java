package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.numeric.NumberRange;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

public final class ClusterGroupIdValidityConstraint {

    private ClusterGroupIdValidityConstraint() { throw new AssertionError(); }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int clusterGroupId
        ) {
            ValidationConstraint<Integer> isPositiveOrZero = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_OR_EQUAL,
                    clusterGroupId,
                    clusterGroup_Id -> clusterGroup_Id >= 0
            );

            ValidationConstraint<Integer> isWithinThreeIntegerDigits = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    clusterGroupId,
                    clusterGroup_Id -> NumericDigitCountMatcher.of(clusterGroup_Id).isDigitCountWithin(
                            ClusterPhysicsMetadata.SystemCluster.ID_MIN_DIGIT,
                            ClusterPhysicsMetadata.SystemCluster.ID_MAX_DIGIT
                    )
            );

            ValidationConstraint<Integer> isWithin0To100 = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    clusterGroupId,
                    clusterGroup_Id -> NumberRange.closed(
                            ClusterPhysicsMetadata.SystemCluster.ID_LOWER_BOUND,
                            ClusterPhysicsMetadata.SystemCluster.ID_UPPER_BOUND
                    ).contains(clusterGroup_Id)
            );

            return isPositiveOrZero
                    .and(isWithinThreeIntegerDigits)
                    .and(isWithin0To100);
        }

        public String requirementDescription() {
            return name +
                    " must be >= 0" +
                    " and must be an " +
                    ClusterPhysicsMetadata.SystemCluster.ID_MIN_DIGIT + " to " +
                    ClusterPhysicsMetadata.SystemCluster.ID_MAX_DIGIT +
                    " digit number" +
                    " and must be within closed range:" +
                    " [" +
                    ClusterPhysicsMetadata.SystemCluster.ID_LOWER_BOUND + ", " +
                    ClusterPhysicsMetadata.SystemCluster.ID_UPPER_BOUND +
                    "]";
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
                ClusterPhysicsMetadata.SystemCluster.ID_DEFAULT_NAME
        );
    }

}
