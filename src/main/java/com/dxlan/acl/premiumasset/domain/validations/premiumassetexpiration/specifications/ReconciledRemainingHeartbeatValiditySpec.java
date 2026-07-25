package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class ReconciledRemainingHeartbeatValiditySpec {

    private ReconciledRemainingHeartbeatValiditySpec() {}

    private static long calculateMaximumDaysBoundary(
            final Instant timeAnchor
    ) {
        return ChronoUnit.DAYS.between(
                timeAnchor,
                ExpirationType.MAXIMUM_EXTENSION.toInstant()
        );
    }

    public static record Definition(
            Instant timeAnchor,
            DomainConcept concept
    ) {

        public Specification<Long> create(
                final long remainingHeartbeat
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.WITHIN,
                    remainingHeartbeat,
                    remaining_Heartbeat -> remaining_Heartbeat >= 0 &&
                            remaining_Heartbeat <= calculateMaximumDaysBoundary(timeAnchor)
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be >= 0 and <= " +
                    calculateMaximumDaysBoundary(timeAnchor) +
                    " (" + PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)";
        }

    }

    public static Definition of(
            final Instant timeAnchor,
            final DomainConcept concept
    ) {
        return new Definition(timeAnchor, concept);
    }

}
