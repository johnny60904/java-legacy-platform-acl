package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.conditions;

import java.time.Instant;

public record ExpirationEqualityCondition(
        Instant expirationTimestamp,
        Instant terminationTimestamp
) {

    public static ExpirationEqualityCondition of(
            final Instant expirationTimestamp,
            final Instant terminationTimestamp
    ) {
        return new ExpirationEqualityCondition(expirationTimestamp, terminationTimestamp);
    }

}
