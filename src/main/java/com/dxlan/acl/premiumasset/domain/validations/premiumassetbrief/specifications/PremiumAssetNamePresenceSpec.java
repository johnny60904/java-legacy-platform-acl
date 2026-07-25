package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.features.shared.text.StringPredicate;

public final class PremiumAssetNamePresenceSpec {

    private PremiumAssetNamePresenceSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<String> create(
                final String assetName
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.NOT_EQUALS,
                    assetName,
                    StringPredicate::isNotBlank
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be specified by valid name";
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
