package com.dxlan.acl.features.shared.time;

import java.time.Instant;

public enum DateFormat {

    UNIX_EPOCH("1970-01-01T00:00:00Z");

    private final String pattern;
    private final Instant absoluteTime;

    private DateFormat(
            final String pattern
    ) {
        this.pattern = pattern;
        this.absoluteTime = Instant.parse(pattern);
    }

    public String getPattern() {
        return pattern;
    }

    public Instant toInstant() {
        return absoluteTime;
    }

}
