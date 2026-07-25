package com.dxlan.acl.premiumasset.application.common.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

import java.util.Locale;

public final class PremiumAssetItemIdRangeRule {

    private PremiumAssetItemIdRangeRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Integer> create(
                final int assetItemId
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.WITHIN,
                    assetItemId,
                    assetItem_Id ->
                            assetItem_Id >= ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND &&
                            assetItem_Id <= ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be within closed range: [" +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND
                    ) + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND
                    ) + "]";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
