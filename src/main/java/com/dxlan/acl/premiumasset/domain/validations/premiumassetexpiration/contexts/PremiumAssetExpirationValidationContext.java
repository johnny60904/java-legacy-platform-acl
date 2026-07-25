package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.contexts;

import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationState;

import java.time.Instant;
import java.time.ZoneId;

public record PremiumAssetExpirationValidationContext(
        DomainConceptProperty<Instant> timeAnchorProperty,
        DomainConceptProperty<ZoneId> timeZoneProperty,
        DomainConceptProperty<Instant> expirationTimestampProperty,
        DomainConceptProperty<Instant> terminationTimestampProperty,
        DomainConceptProperty<Long> remainingHeartbeatProperty,
        DomainConceptProperty<ExpirationState> expirationStateProperty
) {

    public static PremiumAssetExpirationValidationContext of(
            final DomainConceptProperty<Instant> timeAnchorProperty,
            final DomainConceptProperty<ZoneId> timeZoneProperty,
            final DomainConceptProperty<Instant> expirationTimestampProperty,
            final DomainConceptProperty<Instant> terminationTimestampProperty,
            final DomainConceptProperty<Long> remainingHeartbeatProperty,
            final DomainConceptProperty<ExpirationState> expirationStateProperty
    ) {
        return new PremiumAssetExpirationValidationContext(
                timeAnchorProperty,
                timeZoneProperty,
                expirationTimestampProperty,
                terminationTimestampProperty,
                remainingHeartbeatProperty,
                expirationStateProperty
        );
    }

}
