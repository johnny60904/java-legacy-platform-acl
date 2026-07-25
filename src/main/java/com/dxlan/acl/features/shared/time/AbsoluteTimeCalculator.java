package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.numeric.NumericValidator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class AbsoluteTimeCalculator {

    private final Instant absoluteTime;

    private AbsoluteTimeCalculator(
            final Instant absoluteTime
    ) {
        this.absoluteTime = Objects.requireNonNull(
                absoluteTime,
                "AbsoluteTime must not be null."
        );
    }

    public AbsoluteTimeCalculator plus(
            final long amount,
            final LinearTimeUnit linearTimeUnit
    ) {
        NumericValidator.validatePositiveOrZero(amount, "Amount");
        Objects.requireNonNull(linearTimeUnit, "LinearTimeUnit must not be null.");
        if (amount == 0) return this;
        return switch (linearTimeUnit) {
            case MILLISECOND ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plusMillis(amount)
                    );

            case SECOND      ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plusSeconds(amount)
                    );

            case MINUTE      ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plus(amount, ChronoUnit.MINUTES)
                    );

            case HOUR        ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plus(amount, ChronoUnit.HOURS)
                    );

            case DAY         ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plus(amount, ChronoUnit.DAYS)
                    );

            case WEEK        ->
                    new AbsoluteTimeCalculator(
                            absoluteTime.plus(amount, ChronoUnit.WEEKS)
                    );
        };
    }

    public Instant toInstant() {
        return absoluteTime;
    }

    public static AbsoluteTimeCalculator of(
            final Instant absoluteTime
    ) {
        return new AbsoluteTimeCalculator(absoluteTime);
    }

    public static record TimeContext(
            Instant anchorTime,
            ZoneId zoneId
    ) {

        public TimeContext {
            Objects.requireNonNull(anchorTime, "AnchorTime must not be null.");
            Objects.requireNonNull(zoneId, "ZoneId must not be null.");
        }

        public static TimeContext of(
                final Instant anchorTime,
                final ZoneId zone
        ) {
            return new TimeContext(anchorTime, zone);
        }

        public TimeContext plus(
                final long amount,
                final CalendarUnit calendarUnit
        ) {
            NumericValidator.validatePositiveOrZero(amount, "Amount");
            Objects.requireNonNull(calendarUnit, "CalendarUnit must not be null.");
            if (amount == 0) return this;
            ZonedDateTime base = ZonedDateTime.ofInstant(anchorTime, zoneId);
            return switch(calendarUnit) {
                case MILLISECOND -> new TimeContext(
                        base.plus(amount, ChronoUnit.MILLIS).toInstant(),
                        zoneId
                );
                case SECOND      -> new TimeContext(
                        base.plusSeconds(amount).toInstant(),
                        zoneId
                );
                case MINUTE      -> new TimeContext(
                        base.plusMinutes(amount).toInstant(),
                        zoneId
                );
                case HOUR        -> new TimeContext(
                        base.plusHours(amount).toInstant(),
                        zoneId
                );
                case DAY         -> new TimeContext(
                        base.plusDays(amount).toInstant(),
                        zoneId
                );
                case WEEK        -> new TimeContext(
                        base.plusWeeks(amount).toInstant(),
                        zoneId
                );
                case MONTH       -> new TimeContext(
                        base.plusMonths(amount).toInstant(),
                        zoneId
                );
                case YEAR        -> new TimeContext(
                        base.plusYears(amount).toInstant(),
                        zoneId
                );
            };
        }

        public Instant toInstant() {
            return anchorTime;
        }

        public ZonedDateTime toZonedDateTime() {
            return anchorTime.atZone(zoneId);
        }

    }

    public TimeContext atContext(
            final ZoneId zone
    ) {
        return new TimeContext(
                absoluteTime,
                zone
        );
    }

}
