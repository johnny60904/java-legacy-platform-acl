package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum TimeCertaintyCompleteness implements NameDisplayable {

    ABSOLUTE("Absolute"),
    PARTIAL("Partial"),
    COMPLETE("Complete");

    private final String displayName;

    private TimeCertaintyCompleteness(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
