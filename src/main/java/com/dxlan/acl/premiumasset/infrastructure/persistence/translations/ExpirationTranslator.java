package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import net.legacy.platform.core.tme.Timestamp;

import java.time.Instant;

public final class ExpirationTranslator {

    private ExpirationTranslator() {}

    public static Instant toInstant(
            final Timestamp timestamp
    ) {
        return LegacyPlatformTimestampSanitizer.sanitize(timestamp);
    }

    public static Timestamp toLegacyTimestamp(
            final Instant instant
    ) {
        return Timestamp.fromEpochMillis(
                instant.toEpochMilli()
        );
    }

}
