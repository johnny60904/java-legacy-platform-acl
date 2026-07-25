package com.dxlan.acl.premiumasset.domain.enums;

import com.dxlan.acl.features.shared.time.DateFormat;

import java.time.Instant;

public enum ExpirationType {

    PERMANENT("2079-01-01T00:00:00Z"),
    EXPIRED("1900-01-01T00:00:00Z"),
    MAXIMUM_EXTENSION("2078-12-31T00:00:00Z"),
    BASELINE(DateFormat.UNIX_EPOCH.getPattern());

    private final String pattern;
    private final Instant absoluteTime;

    private ExpirationType(final String pattern) {
        this.pattern = pattern;
        this.absoluteTime = Instant.parse(pattern);
    }

    public String pattern() {
        return this.pattern;
    }

    public Instant toInstant() {
        return this.absoluteTime;
    }

}
