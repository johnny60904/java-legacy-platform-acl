package com.dxlan.acl.features.shared.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class CompleteTimeFormatter {

    private final TimePattern timePattern;

    private CompleteTimeFormatter(
            final TimePattern timePattern
    ) {
        this.timePattern = Objects.requireNonNull(
                timePattern,
                TimePattern.class.getSimpleName() + " must not be null."
        );
    }

    public String format(
            final ZonedDateTime completeTime
    ) {
        Objects.requireNonNull(completeTime, "CompleteTime must not be null.");
        DateTimeFormatter formatter = timePattern
                .getFormatterWithZone(completeTime.getZone());
        return formatter.format(completeTime);
    }

    public static CompleteTimeFormatter ofPattern(
            final TimePattern timePattern
    ) {
        return new CompleteTimeFormatter(timePattern);
    }

}
