package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.NameDisplayable;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public enum TimePattern implements NameDisplayable {

    /// similar to instant.toString()
    DEFAULT(
            "Default",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    ),

    /// e.g. 2026-06-16 15:30:00
    DATETIME_STANDARD(
            "DateTimeStandard",
            "yyyy-MM-dd HH:mm:ss"
    ),

    /// e.g. 2026-06-16
    DATE_STANDARD(
            "DateStandard",
            "yyyy-MM-dd"
    ),

    /// e.g. 15:30:00
    TIME_STANDARD(
            "TimeStandard",
            "HH:mm:ss"
    ),

    /// e.g. 20260616153000
    DATETIME_COMPACT(
            "DateTimeCompact",
            "yyyyMMddHHmmss"
    ),

    /// e.g. 20260616
    DATE_COMPACT(
            "DateCompact",
            "yyyyMMdd"
    ),

    /// ISO 8601, e.g. 2026-06-16T15:30:00.000+08:00
    ISO_WITH_OFFSET(
            "ISOWithOffset",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    ),

    /// e.g. 2026-06-16 15:30:00 +0800
    DATETIME_WITH_OFFSET(
            "DateTimeWithOffset",
            "yyyy-MM-dd HH:mm:ss Z"
    ),

    /// e.g. 2026-06-16 15:30:00 Asia/Taipei
    DATETIME_WITH_ZONE(
            "DateTimeWithZone",
            "yyyy-MM-dd HH:mm:ss VV"
    );

    private final String displayName;
    private final String pattern;
    private final DateTimeFormatter formatter;

    private TimePattern(
            final String displayName,
            final String pattern
    ) {
        this.displayName = displayName;
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String displayName() {
        return displayName;
    }

    public String getPattern() {
        return pattern;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public DateTimeFormatter getFormatterWithZone(
            final ZoneId zoneId
    ) {
        return formatter.withZone(zoneId);
    }

}
