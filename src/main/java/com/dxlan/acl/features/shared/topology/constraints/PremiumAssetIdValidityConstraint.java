package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.numeric.NumericLeadingDigitMatcher;
import com.dxlan.acl.features.shared.numeric.NumberRange;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

import java.util.Locale;

public final class PremiumAssetIdValidityConstraint {

    private PremiumAssetIdValidityConstraint() { throw new AssertionError(); }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int premiumAssetItemId
        ) {
            ValidationConstraint<Integer> isPositive = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_THAN,
                    premiumAssetItemId,
                    premiumAssetItem_Id -> premiumAssetItem_Id > 0
            );

            ValidationConstraint<Integer> isSeptupleDigit = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.EQUALS,
                    premiumAssetItemId,
                    premiumAssetItem_Id -> NumericDigitCountMatcher
                            .of(premiumAssetItem_Id)
                            .isDigitCount(
                                    ClusterPhysicsMetadata.PremiumAssetItem.ID_DIGIT_COUNT
                            )
            );

            ValidationConstraint<Integer> isFirstDigitFive = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.EQUALS,
                    premiumAssetItemId,
                    premiumAssetItem_Id -> NumericLeadingDigitMatcher
                            .of(premiumAssetItem_Id)
                            .isFirstDigit(
                                    ClusterPhysicsMetadata.PremiumAssetItem.ID_FIRST_DIGIT
                            )
            );

            ValidationConstraint<Integer> isWithin5000000To5009999 = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    premiumAssetItemId,
                    premiumAssetItem_Id -> NumberRange.closed(
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND,
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND
                    ).contains(premiumAssetItem_Id)
            );

            return isPositive
                    .and(isSeptupleDigit)
                    .and(isFirstDigitFive)
                    .and(isWithin5000000To5009999);
        }

        public String requirementDescription() {
            return name +
                    " must be > 0" +
                    " and must be a " +
                    ClusterPhysicsMetadata.PremiumAssetItem.ID_DIGIT_COUNT +
                    "-digit number" +
                    " and must be begin with " +
                    ClusterPhysicsMetadata.PremiumAssetItem.ID_FIRST_DIGIT +
                    " (first digit)" +
                    " and must be within closed range:" +
                    " [" +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND
                    ) + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND
                    ) +
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
                ClusterPhysicsMetadata.PremiumAssetItem.ID_DEFAULT_NAME
        );
    }

}
