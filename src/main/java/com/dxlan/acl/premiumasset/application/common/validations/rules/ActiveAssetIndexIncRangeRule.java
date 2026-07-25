package com.dxlan.acl.premiumasset.application.common.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public final class ActiveAssetIndexIncRangeRule {

    private ActiveAssetIndexIncRangeRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Integer> create(
                final int activeAssetIndex
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.WITHIN,
                    activeAssetIndex,
                    activeAsset_Index ->
                            activeAsset_Index >= ClusterPhysicsMetadata.ActiveAsset.INDEX_LOWER_BOUND &&
                            activeAsset_Index <= ClusterPhysicsMetadata.ActiveAsset.INDEX_UPPER_BOUND
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be within closed range: [" +
                    ClusterPhysicsMetadata.ActiveAsset.INDEX_LOWER_BOUND + ", " +
                    ClusterPhysicsMetadata.ActiveAsset.INDEX_UPPER_BOUND + "]";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
