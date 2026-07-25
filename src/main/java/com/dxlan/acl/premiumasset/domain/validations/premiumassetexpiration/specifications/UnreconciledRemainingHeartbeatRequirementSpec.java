package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;

public final class UnreconciledRemainingHeartbeatRequirementSpec {

    private UnreconciledRemainingHeartbeatRequirementSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Long> create(
                final long remainingHeartbeat
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.GREATER_OR_EQUAL,
                    remainingHeartbeat,
                    remaining_Heartbeat -> remaining_Heartbeat >= 0
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be >= 0";
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
