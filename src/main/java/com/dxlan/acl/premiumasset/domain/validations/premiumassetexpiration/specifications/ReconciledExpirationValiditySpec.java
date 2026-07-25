package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;
import com.dxlan.acl.premiumasset.domain.components.ExpirationExtensor;

import java.time.Instant;

public final class ReconciledExpirationValiditySpec {

    private ReconciledExpirationValiditySpec() {}

    public static record Definition(
            Instant timeAnchor,
            DomainConcept concept
    ) {

        public Specification<Instant> create(
                final Instant expirationTime
        ) {
            Specification<Instant> isReconciledExpired = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.EXPIRED.toInstant()
                    )
            );

            Specification<Instant> isReconciledPermanent = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.PERMANENT.toInstant()
                    )
            );

            Specification<Instant> isReconciledTimed = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationExtensor.of(timeAnchor).extend(
                                    PremiumAssetLifeCycle.STANDARD_SUBSCRIPTION.defaultDays(),
                                    ExpirationUnit.DAY
                            ).toInstant()
                    )
            );

            Specification<Instant> isBelowUpperBound = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.LESS_OR_EQUAL,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.MAXIMUM_EXTENSION.toInstant()
                    ) || expiration_Time.isBefore(
                            ExpirationType.MAXIMUM_EXTENSION.toInstant()
                    )
            );

            return isReconciledExpired
                    .or(isReconciledPermanent)
                    .or(
                            isReconciledTimed.and(isBelowUpperBound)
                    );

        }

        public String ruleDescription() {
            return "Reconciled " + concept.displayName() +
                    " must be specified and must be a system constant: "+
                    " '" + ExpirationType.EXPIRED.pattern() + "'" +
                    ", '" + ExpirationType.PERMANENT.pattern() + "'" +
                    ", or a valid future date before '" +
                    ExpirationType.MAXIMUM_EXTENSION.pattern() + "'";
        }

    }

    public static Definition of(
            final Instant timeAnchor,
            final DomainConcept concept
    ) {
        return new Definition(timeAnchor, concept);
    }

}