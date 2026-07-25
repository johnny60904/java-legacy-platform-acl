package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

public final class ActiveAssetIndexValidityConstraint {

    private ActiveAssetIndexValidityConstraint() {
        throw new AssertionError();
    }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int activeAssetIndex
        ) {
            ValidationConstraint<Integer> isPositiveOrZero = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_OR_EQUAL,
                    activeAssetIndex,
                    activeAsset_Index -> activeAsset_Index >= 0
            );

            ValidationConstraint<Integer> isDigitCountOne = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.EQUALS,
                    activeAssetIndex,
                    activeAsset_Index -> NumericDigitCountMatcher
                            .of(activeAsset_Index)
                            .isDigitCount(
                                    ClusterPhysicsMetadata.ActiveAsset.INDEX_DIGIT_COUNT
                            )
            );

            ValidationConstraint<Integer> isWithin0To2 = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    activeAssetIndex,
                    activeAsset_Index ->
                            activeAsset_Index >= ClusterPhysicsMetadata.ActiveAsset.INDEX_LOWER_BOUND &&
                            activeAsset_Index <= ClusterPhysicsMetadata.ActiveAsset.INDEX_UPPER_BOUND
            );

            return isPositiveOrZero
                    .and(isDigitCountOne)
                    .and(isWithin0To2);
        }

        public String requirementDescription() {
            return name +
                    " must be >= 0" +
                    " and must be an " +
                    ClusterPhysicsMetadata.ActiveAsset.INDEX_DIGIT_COUNT +
                    "-digit number" +
                    " and must be within closed range:" +
                    " [" +
                    ClusterPhysicsMetadata.ActiveAsset.INDEX_LOWER_BOUND + ", " +
                    ClusterPhysicsMetadata.ActiveAsset.INDEX_UPPER_BOUND +
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
                ClusterPhysicsMetadata.ActiveAsset.INDEX_DEFAULT_NAME
        );
    }

}
