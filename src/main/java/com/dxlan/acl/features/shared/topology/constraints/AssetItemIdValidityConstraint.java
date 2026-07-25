package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.collections.SetJoiner;
import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.numeric.NumericLeadingDigitMatcher;
import com.dxlan.acl.features.shared.numeric.NumberRange;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

import java.util.Locale;

public final class AssetItemIdValidityConstraint {

    private AssetItemIdValidityConstraint() { throw new AssertionError(); }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int assetItemId
        ) {
            ValidationConstraint<Integer> isPositive = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_THAN,
                    assetItemId,
                    assetItem_Id -> assetItem_Id > 0
            );

            ValidationConstraint<Integer> is7Or8DigitCount = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    assetItemId,
                    assetItem_Id -> NumericDigitCountMatcher
                            .of(assetItem_Id)
                            .isDigitCountWithin(
                                    ClusterPhysicsMetadata.AssetEntity.ID_MIN_DIGIT,
                                    ClusterPhysicsMetadata.AssetEntity.ID_MAX_DIGIT
                            )
            );

            ValidationConstraint<Integer> isFirstDigitValid = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.ANY_OF,
                    assetItemId,
                    assetItem_Id -> NumericLeadingDigitMatcher
                            .of(assetItem_Id)
                            .isFirstDigitAnyOf(
                                    ClusterPhysicsMetadata.AssetEntity.ID_ALLOWED_FIRST_DIGITS
                            )
            );

            ValidationConstraint<Integer> isWithinAnyOfValidRanges = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    assetItemId,
                    assetItem_Id -> NumberRange.closed(
                            ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MIN,
                            ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MAX
                    ).contains(assetItem_Id) || NumberRange.closed(
                            ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MIN,
                            ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MAX
                    ).contains(assetItem_Id)
            );

            return isPositive
                    .and(is7Or8DigitCount)
                    .and(isFirstDigitValid)
                    .and(isWithinAnyOfValidRanges);
        }

        public String requirementDescription() {
            return name +
                    " must be > 0" +
                    " and must be an " +
                    ClusterPhysicsMetadata.AssetEntity.ID_MIN_DIGIT + " to " +
                    ClusterPhysicsMetadata.AssetEntity.ID_MAX_DIGIT +
                    " digit number" +
                    " and must be begin with one of the following first digits: " +
                    SetJoiner.join(ClusterPhysicsMetadata.AssetEntity.ID_ALLOWED_FIRST_DIGITS) +
                    " and must be within either valid closed range:" +
                    " [" +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MIN
                    ) + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MAX
                    ) + "] or " +
                    " [" +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MIN
                    ) + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MAX
                    ) + "]";
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
                ClusterPhysicsMetadata.AssetEntity.ID_DEFAULT_NAME
        );
    }

}
