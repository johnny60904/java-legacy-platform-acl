package com.dxlan.acl.features.shared.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public enum TemporalType {

    INSTANT(Instant.class.getSimpleName()),
    LOCAL_DATE_TIME(LocalDateTime.class.getSimpleName()),
    ZONED_DATE_TIME(ZonedDateTime.class.getSimpleName());

    private final String displayName;

    private  TemporalType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

}
