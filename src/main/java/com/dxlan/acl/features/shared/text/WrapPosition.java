package com.dxlan.acl.features.shared.text;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum WrapPosition implements NameDisplayable {

    NONE("None"),
    START("Start"),
    END("End"),
    BOTH("Both");

    private final String displayName;

    private WrapPosition(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
