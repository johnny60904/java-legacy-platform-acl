package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum TimeZoneContext implements NameDisplayable {

    UNIVERSAL("Universal"),
    FLOATING("Floating"),
    ZONED("Zoned");

    private final String displayName;

    private TimeZoneContext(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
