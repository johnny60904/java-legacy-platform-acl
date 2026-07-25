package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.numeric.NumericValidator;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class CompleteTimeCalculator {

    private final ZonedDateTime completeTime;

    private CompleteTimeCalculator(
            final ZonedDateTime completeTime
    ) {
        this.completeTime = Objects.requireNonNull(
                completeTime,
                "CompleteTime must not be null."
        );
    }

    public CompleteTimeCalculator plus(
            final long amount,
            final CalendarUnit calendarUnit
    ) {
        NumericValidator.validatePositiveOrZero(amount, "Amount");
        Objects.requireNonNull(calendarUnit, "CalendarUnit must not be null.");
        if (amount == 0) return this;
        return switch(calendarUnit) {
            case MILLISECOND -> new CompleteTimeCalculator(
                    completeTime.plus(amount, ChronoUnit.MILLIS)
            );
            case SECOND      -> new CompleteTimeCalculator(
                    completeTime.plusSeconds(amount)
            );
            case MINUTE      -> new CompleteTimeCalculator(
                    completeTime.plusMinutes(amount)
            );
            case HOUR        -> new CompleteTimeCalculator(
                    completeTime.plusHours(amount)
            );
            case DAY         -> new CompleteTimeCalculator(
                    completeTime.plusDays(amount)
            );
            case WEEK        -> new CompleteTimeCalculator(
                    completeTime.plusWeeks(amount)
            );
            case MONTH       -> new CompleteTimeCalculator(
                    completeTime.plusMonths(amount)
            );
            case YEAR        -> new CompleteTimeCalculator(
                    completeTime.plusYears(amount)
            );
        };
    }

    public Instant toInstant() {
        return completeTime.toInstant();
    }

    public ZonedDateTime toZonedDateTime() {
        return completeTime;
    }

    public static CompleteTimeCalculator of(
            final ZonedDateTime completeTime
    ) {
        return new CompleteTimeCalculator(completeTime);
    }

}
