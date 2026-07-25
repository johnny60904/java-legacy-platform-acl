package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;

import java.time.Instant;
import java.util.Objects;

public final class SanitizedExpirationBaselineSpec {

    private SanitizedExpirationBaselineSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Instant> create(
                final Instant expirationTime
        ) {
            Specification<Instant> isSanitizedExpired = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.EXPIRED.toInstant()
                    )
            );

            Specification<Instant> isSanitizedPermanent = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.PERMANENT.toInstant()
                    )
            );

            Specification<Instant> isSanitizedMaximumFuture = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.MAXIMUM_EXTENSION.toInstant()
                    )
            );

            Specification<Instant> isSanitizedMinimumPast = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.EQUALS,
                    expirationTime,
                    expiration_Time -> expiration_Time.equals(
                            ExpirationType.BASELINE.toInstant()
                    )
            );

            Specification<Instant> isValidInstant = new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.NOT_EQUALS,
                    expirationTime,
                    Objects::nonNull
            );

            return isSanitizedExpired
                    .or(isSanitizedPermanent)
                    .or(isSanitizedMinimumPast)
                    .or(isSanitizedMaximumFuture)
                    .or(isValidInstant);

        }

        public String ruleDescription() {
            return "Unreconciled " + concept.displayName() +
                    " must be specified" +
                    " and must hold a sanitized baseline constant: " +
                    " '" + ExpirationType.EXPIRED.pattern() + "'" +
                    ", '" + ExpirationType.PERMANENT.pattern() + "'" +
                    ", '" + ExpirationType.MAXIMUM_EXTENSION.pattern() + "'" +
                    ", '" + ExpirationType.BASELINE.pattern() + "'" +
                    ", or any valid date";
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
