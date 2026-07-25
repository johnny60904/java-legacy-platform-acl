package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;
import com.dxlan.acl.premiumasset.domain.components.ExtensionDurationCalculator;

import java.time.Instant;
import java.time.ZoneId;

public final class ExtensionDurationBoundsSpec {

    private ExtensionDurationBoundsSpec() {}

    private static long calculateMaximumBound(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final ExpirationUnit expirationUnit
    ) {
        return ExtensionDurationCalculator
                .of(timeAnchor, timeZone)
                .calculateUpperBoundIn(expirationUnit);
    }

    public static record Definition(
            Instant timeAnchor,
            ZoneId timeZone,
            ExpirationUnit expirationUnit,
            DomainConcept concept
    ) {

        public Specification<Long> create(
                final long extensionDuration
        ) {
            long maximumBound = calculateMaximumBound(
                    timeAnchor,
                    timeZone,
                    expirationUnit
            );
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.WITHIN,
                    extensionDuration,
                    extension_Duration -> extension_Duration > 0 &&
                            extension_Duration <= maximumBound
            );
        }

        public String ruleDescription() {
            long maximumBound = calculateMaximumBound(
                    timeAnchor,
                    timeZone,
                    expirationUnit
            );
            return concept.displayName() +
                    " must be > 0 and <= " +
                    maximumBound;

        }

    }

    public static Definition of(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final ExpirationUnit expirationUnit,
            final DomainConcept concept
    ) {
        return new Definition(
                timeAnchor,
                timeZone,
                expirationUnit,
                concept
        );
    }

}
