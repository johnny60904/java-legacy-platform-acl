package com.dxlan.acl.features.shared.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class AbsoluteTimeFormatter {

    private final TimePattern timePattern;

    private AbsoluteTimeFormatter(
            final TimePattern timePattern
    ) {
        this.timePattern = Objects.requireNonNull(
                timePattern,
                TimePattern.class.getSimpleName() + " must not be null."
        );
    }

    public String formatWithZone(
            final Instant absoluteTime,
            final ZoneId zoneId
    ) {
        Objects.requireNonNull(absoluteTime, "AbsoluteTime must not be null.");
        Objects.requireNonNull(zoneId, "ZoneId must not be null.");
        DateTimeFormatter formatter = timePattern.getFormatterWithZone(zoneId);
        return formatter.format(absoluteTime);
    }

    public static AbsoluteTimeFormatter ofPattern(
            final TimePattern timePattern
    ) {
        return new AbsoluteTimeFormatter(timePattern);
    }

}
