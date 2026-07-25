package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications;

import com.dxlan.acl.features.shared.arrays.ArrayJoiner;
import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;

public final class LifespanDiscreteRangeSpec {

    private LifespanDiscreteRangeSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Long> create(
                final long lifespan
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.CONTAINS,
                    lifespan,
                    PremiumAssetLifeCycle::contains
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " (" + PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)" +
                    " must be one of valid value: " +
                    ArrayJoiner.join(PremiumAssetLifeCycle.ALLOWED_DAYS);
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
