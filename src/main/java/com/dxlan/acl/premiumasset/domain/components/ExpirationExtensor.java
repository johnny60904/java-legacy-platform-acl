package com.dxlan.acl.premiumasset.domain.components;

import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public final class ExpirationExtensor {

    private final Instant timeAnchor;
    private final ZoneId timeZone;

    private ExpirationExtensor(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        this.timeAnchor = timeAnchor;
        this.timeZone = timeZone;
    }

    private ExpirationExtensor (
            final Instant timeAnchor
    ) {
        this.timeAnchor = timeAnchor;
        this.timeZone = ZoneId.of("UTC");
    }

    private Instant extendDays(
            final long duration
    ) {
        return timeAnchor.plus(
                duration,
                ChronoUnit.DAYS
        );
    }

    private Instant extendWeeks(
            final long duration
    ) {
        return timeAnchor.plus(
                duration,
                ChronoUnit.WEEKS
        );
    }

    private Instant extendMonths(
            final long duration
    ) {
        return ZonedDateTime.ofInstant(timeAnchor, timeZone)
                .plusMonths(duration)
                .toInstant();
    }

    private Instant extendYears(
            final long duration
    ) {
        return ZonedDateTime.ofInstant(timeAnchor, timeZone)
                .plusYears(duration)
                .toInstant();
    }

    public ExpirationExtensor extend(
            final long duration,
            final ExpirationUnit expirationUnit
    ) {
        Instant newAnchor = switch(expirationUnit) {
            case DAY -> extendDays(duration);
            case WEEK -> extendWeeks(duration);
            case MONTH -> extendMonths(duration);
            case YEAR -> extendYears(duration);
        };
        return new ExpirationExtensor(newAnchor, timeZone);
    }

    public Instant toInstant() {
        return this.timeAnchor;
    }

    public static ExpirationExtensor of(
            final Instant timeAnchor
    ) {
        return new ExpirationExtensor(timeAnchor);
    }

    public static ExpirationExtensor of(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return new ExpirationExtensor(
                timeAnchor,
                timeZone
        );
    }

    private static ExpirationExtensor decideTimeContext(
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return switch(expirationUnit) {
            case DAY, WEEK -> new ExpirationExtensor(timeAnchor);
            case MONTH, YEAR -> new ExpirationExtensor(timeAnchor, timeZone);
        };
    }

    public static ExpirationExtensor of(
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return decideTimeContext(expirationUnit, timeAnchor, timeZone);
    }

}
