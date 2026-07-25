package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum DigitStrategy implements NameDisplayable {

    INTEGER("Integer"),
    SCALE("Scale"),
    TOTAL("Total");

    private final String displayName;

    private DigitStrategy(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
