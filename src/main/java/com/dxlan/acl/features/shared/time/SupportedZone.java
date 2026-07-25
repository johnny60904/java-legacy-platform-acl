package com.dxlan.acl.features.shared.time;

import java.time.ZoneId;

public enum SupportedZone {

    UTC("UTC");

    private final String id;
    private final ZoneId timeZone;

    private SupportedZone(
            final String id
    ) {
        this.id = id;
        this.timeZone = ZoneId.of(id);
    }

    public String getId() {
        return id;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

}
