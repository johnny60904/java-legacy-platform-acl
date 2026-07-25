package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts;

import java.time.Instant;
import java.time.ZoneId;

public record ExpirationTimeContext(
        Instant timeAnchor,
        ZoneId timeZone
) {

    public static ExpirationTimeContext of(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return new ExpirationTimeContext(
                timeAnchor,
                timeZone
        );
    }

}
