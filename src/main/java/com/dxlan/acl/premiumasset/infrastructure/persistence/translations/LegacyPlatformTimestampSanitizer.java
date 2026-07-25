package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;
import net.legacy.platform.core.tme.Timestamp;

import java.time.Instant;

public final class LegacyPlatformTimestampSanitizer {

    private LegacyPlatformTimestampSanitizer() { throw new AssertionError(); }

    public static Instant sanitize(
            final Timestamp timestamp
    ) {
        if (
                (timestamp == null) ||
                (timestamp.toLong() == 0) ||
                (timestamp.toEpochMillis() == 0)
        ) {
            return ExpirationType.BASELINE.toInstant();
        } else if (
                (timestamp.isBoundaryMax()) ||
                (Instant.ofEpochMilli(timestamp.toEpochMillis()).equals(
                        Instant.ofEpochMilli(Timestamp.boundaryMax().toEpochMillis())
                ))
        ) {
            return ExpirationType.PERMANENT.toInstant();
        } else if (Instant.ofEpochMilli(timestamp.toEpochMillis()).isAfter(
                Instant.ofEpochMilli(Timestamp.boundaryMax().toEpochMillis())
        )) {
            return ExpirationType.MAXIMUM_EXTENSION.toInstant();
        } else if (
                (timestamp.isBoundaryMin()) ||
                (Instant.ofEpochMilli(timestamp.toEpochMillis()).equals(
                        Instant.ofEpochMilli(Timestamp.boundaryMin().toEpochMillis())
                )) ||
                (Instant.ofEpochMilli(timestamp.toEpochMillis()).isBefore(
                        Instant.ofEpochMilli(Timestamp.boundaryMin().toEpochMillis())
                ))
        ) {
            return ExpirationType.EXPIRED.toInstant();
        } else {
            return Instant.ofEpochMilli(timestamp.toEpochMillis());
        }
    }

}
