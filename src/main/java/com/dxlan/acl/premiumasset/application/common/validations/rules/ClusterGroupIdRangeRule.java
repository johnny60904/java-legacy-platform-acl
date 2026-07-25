package com.dxlan.acl.premiumasset.application.common.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public final class ClusterGroupIdRangeRule {

    private ClusterGroupIdRangeRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Integer> create(
                final int clusterGroupId
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.WITHIN,
                    clusterGroupId,
                    clusterGroup_Id ->
                            clusterGroup_Id >= ClusterPhysicsMetadata.SystemCluster.ID_LOWER_BOUND &&
                            clusterGroup_Id <= ClusterPhysicsMetadata.SystemCluster.ID_UPPER_BOUND
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be within closed range: [" +
                    ClusterPhysicsMetadata.SystemCluster.ID_LOWER_BOUND + ", " +
                    ClusterPhysicsMetadata.SystemCluster.ID_UPPER_BOUND + "]";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
