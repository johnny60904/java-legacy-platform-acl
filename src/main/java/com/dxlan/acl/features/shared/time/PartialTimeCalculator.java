package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.numeric.NumericValidator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class PartialTimeCalculator {

    private final LocalDateTime partialTime;

    private PartialTimeCalculator(
            final LocalDateTime partialTime
    ) {
        this.partialTime = Objects.requireNonNull(
                partialTime,
                "PartialTime must not be null."
        );
    }

    public PartialTimeCalculator plus(
            final long amount,
            final ChronologicalScale chronologicalScale
    ) {
        NumericValidator.validatePositiveOrZero(amount, "Amount");
        Objects.requireNonNull(chronologicalScale, "ChronologicalScale must not be null.");
        if (amount == 0) return this;
        return switch (chronologicalScale) {
            case MILLISECOND ->
                    new PartialTimeCalculator(
                            partialTime.plus(amount, ChronoUnit.MILLIS)
                    );

            case SECOND      ->
                    new PartialTimeCalculator(
                            partialTime.plusSeconds(amount)
                    );

            case MINUTE      ->
                    new PartialTimeCalculator(
                            partialTime.plusMinutes(amount)
                    );

            case HOUR        ->
                    new PartialTimeCalculator(
                            partialTime.plusHours(amount)
                    );

            case DAY         ->
                    new PartialTimeCalculator(
                            partialTime.plusDays(amount)
                    );

            case WEEK        ->
                    new PartialTimeCalculator(
                            partialTime.plusWeeks(amount)
                    );
            case MONTH        ->
                    new PartialTimeCalculator(
                            partialTime.plusMonths(amount)
                    );
            case YEAR        ->
                    new PartialTimeCalculator(
                            partialTime.plusYears(amount)
                    );
        };
    }

    public LocalDateTime toLocalDateTime() {
        return partialTime;
    }

    public static PartialTimeCalculator of(
            final LocalDateTime partialTime
    ) {
        return new PartialTimeCalculator(partialTime);
    }

    public static record TimeContext(
            ZonedDateTime completeTime
    ) {

        public TimeContext {
            Objects.requireNonNull(completeTime, "CompleteTime must not be null.");
        }

        public static TimeContext of(
                final ZonedDateTime completeTime
        ) {
            return new TimeContext(completeTime);
        }

        public TimeContext plus(
                final long amount,
                final CalendarUnit calendarUnit
        ) {
            NumericValidator.validatePositiveOrZero(amount, "Amount");
            Objects.requireNonNull(calendarUnit, "CalendarUnit must not be null.");
            if (amount == 0) return this;
            return switch(calendarUnit) {
                case MILLISECOND -> new TimeContext(
                        completeTime.plus(amount, ChronoUnit.MILLIS)
                );
                case SECOND      -> new TimeContext(
                        completeTime.plusSeconds(amount)
                );
                case MINUTE      -> new TimeContext(
                        completeTime.plusMinutes(amount)
                );
                case HOUR        -> new TimeContext(
                        completeTime.plusHours(amount)
                );
                case DAY         -> new TimeContext(
                        completeTime.plusDays(amount)
                );
                case WEEK        -> new TimeContext(
                        completeTime.plusWeeks(amount)
                );
                case MONTH       -> new TimeContext(
                        completeTime.plusMonths(amount)
                );
                case YEAR        -> new TimeContext(
                        completeTime.plusYears(amount)
                );
            };
        }

        public Instant toInstant() {
            return completeTime.toInstant();
        }

        public ZonedDateTime toZonedDateTime() { return completeTime; }

    }

    public TimeContext atContext(
            final ZoneId zoneId
    ) {
        Objects.requireNonNull(zoneId, "ZoneId must not be null.");
        return new TimeContext(
                partialTime.atZone(zoneId)
        );
    }

}
