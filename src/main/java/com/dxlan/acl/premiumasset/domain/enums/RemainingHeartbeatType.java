package com.dxlan.acl.premiumasset.domain.enums;

public enum RemainingHeartbeatType {

    TIMED("Timed", 90L),
    EXPIRED("Expired", 0L),
    PERMANENT("Permanent", 0L);

    private final String displayName;
    private final long days;

    private RemainingHeartbeatType(
            final String displayName,
            final long days
    ) {
        this.displayName = displayName;
        this.days = days;
    }

    public String displayName() {
        return displayName;
    }

    public long days() {
        return days;
    }
}
