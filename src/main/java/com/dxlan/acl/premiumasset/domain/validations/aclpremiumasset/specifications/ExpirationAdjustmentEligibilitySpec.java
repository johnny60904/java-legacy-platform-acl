package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;

public final class ExpirationAdjustmentEligibilitySpec {

    private ExpirationAdjustmentEligibilitySpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<PremiumAssetType> create(
                final PremiumAssetType premiumAssetType
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    premiumAssetType,
                    premiumAsset_Type -> premiumAsset_Type == PremiumAssetType.TIMED
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be '" + PremiumAssetType.TIMED.displayName() + "'" +
                    " (only " +
                    PremiumAssetType.TIMED.description() +
                    " can be extended or expired expiration).";
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(
                concept
        );
    }

}
