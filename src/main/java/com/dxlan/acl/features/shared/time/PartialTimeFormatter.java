package com.dxlan.acl.features.shared.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class PartialTimeFormatter {

    private final TimePattern timePattern;

    private PartialTimeFormatter(
            final TimePattern timePattern
    ) {
        Objects.requireNonNull(
                timePattern,
                TimePattern.class.getSimpleName() + " must not be null."
        );
        if (
                (timePattern == TimePattern.ISO_WITH_OFFSET) ||
                (timePattern == TimePattern.DATETIME_WITH_OFFSET) ||
                (timePattern == TimePattern.DATETIME_WITH_ZONE)
        ) {
            throw new UnsupportedOperationException(
                    "Unsupported TimePattern: " + timePattern.displayName() + "."
            );
        }
        this.timePattern = timePattern;
    }

    public String formatWithZone(
            final LocalDateTime partialTime,
            final ZoneId zoneId
    ) {
        Objects.requireNonNull(partialTime, "PartialTime must not be null.");
        Objects.requireNonNull(zoneId, "ZoneId must not be null.");
        DateTimeFormatter formatter = timePattern.getFormatterWithZone(zoneId);
        return formatter.format(partialTime);
    }

    public static PartialTimeFormatter ofPattern(
            final TimePattern timePattern
    ) {
        return new PartialTimeFormatter(timePattern);
    }

}
