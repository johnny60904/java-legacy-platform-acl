package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.conditions.ExpirationEqualityCondition;

public final class ExpirationEqualitySpec {

    private ExpirationEqualitySpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<ExpirationEqualityCondition> create(
                final ExpirationEqualityCondition expirationEqualityCondition
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationEqualityCondition,
                    condition -> condition.expirationTimestamp()
                            .equals(condition.terminationTimestamp())
            );
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
