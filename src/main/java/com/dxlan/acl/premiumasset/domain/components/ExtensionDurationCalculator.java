package com.dxlan.acl.premiumasset.domain.components;

import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public final class ExtensionDurationCalculator {

    private final Instant timeAnchor;
    private final ZoneId timeZone;

    private static final Instant UPPER_BOUND =
            ExpirationType.MAXIMUM_EXTENSION.toInstant();

    private ExtensionDurationCalculator(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        this.timeAnchor = timeAnchor;
        this.timeZone = timeZone;
    }

    private long calculateDaysUpperBound() {
        return ChronoUnit.DAYS.between(
                timeAnchor,
                UPPER_BOUND
        );
    }

    private long calculateWeeksUpperBound() {
        return ChronoUnit.WEEKS.between(
                timeAnchor,
                UPPER_BOUND
        );
    }

    private long calculateMonthsUpperBound() {
        ZonedDateTime start = ZonedDateTime.ofInstant(timeAnchor, timeZone);
        ZonedDateTime end = ZonedDateTime.ofInstant(UPPER_BOUND, timeZone) ;
        return ChronoUnit.MONTHS.between(
                start,
                end
        );
    }

    private long calculateYearsUpperBound() {
        ZonedDateTime start = ZonedDateTime.ofInstant(timeAnchor, timeZone);
        ZonedDateTime end = ZonedDateTime.ofInstant(UPPER_BOUND, timeZone) ;
        return ChronoUnit.YEARS.between(
                start,
                end
        );
    }

    public long calculateUpperBoundIn(
            final ExpirationUnit expirationUnit
    ) {
        return switch(expirationUnit) {
            case DAY -> calculateDaysUpperBound();
            case WEEK -> calculateWeeksUpperBound();
            case MONTH -> calculateMonthsUpperBound();
            case YEAR -> calculateYearsUpperBound();
        };
    }

    public static ExtensionDurationCalculator of(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return new ExtensionDurationCalculator(
                timeAnchor,
                timeZone
        );
    }

}
