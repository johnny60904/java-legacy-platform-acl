package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetItemIdAllocation;

public final class PremiumAssetItemIdBoundsSpec {

    private PremiumAssetItemIdBoundsSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Integer> create(
                final int assetItemId
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.WITHIN,
                    assetItemId,
                    assetItem_Id -> PremiumAssetItemIdAllocation.CORE
                            .idRange()
                            .contains(assetItem_Id)
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be within closed range: " +
                    PremiumAssetItemIdAllocation.CORE
                            .idRange()
                            .toString();
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
